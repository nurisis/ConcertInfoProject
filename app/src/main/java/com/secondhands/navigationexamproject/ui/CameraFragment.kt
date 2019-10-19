package com.secondhands.navigationexamproject.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.secondhands.navigationexamproject.BuildConfig
import com.secondhands.navigationexamproject.R
import com.secondhands.navigationexamproject.databinding.CameraFragmentBinding
import kotlinx.android.synthetic.main.camera_fragment.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class CameraFragment : Fragment() {

    private var cameraFilePath: String? = null
    private val CAMERA_REQUEST_CODE = 111

    companion object {
        fun newInstance() = CameraFragment()
    }

    private lateinit var viewDataBinding:CameraFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = CameraFragmentBinding.inflate(inflater, container, false)

        captureFromCamera()

        return viewDataBinding.root
    }

    private fun captureFromCamera() {
        try {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N){
                val uri = FileProvider.getUriForFile(activity!!, BuildConfig.APPLICATION_ID + ".provider", createImageFile())
                intent.putExtra(
                    MediaStore.EXTRA_OUTPUT,
                    uri
                )
                Log.d("LOG>>","Uri : ${uri}")
            }
            else {
                intent.putExtra(
                    MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(createImageFile())
                )
            }

            startActivityForResult(intent, CAMERA_REQUEST_CODE)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }

    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        //This is the directory in which the file will be created. This is the default location of Camera photos
        val storageDir = File(
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM
            ), activity!!.getString(R.string.app_name)
        )

        if(!storageDir.exists())
            storageDir.mkdir()

        Log.d("LOG>>","Directory : ${storageDir.absolutePath}")

        val image = File.createTempFile(
            imageFileName, /* prefix */
            ".jpg", /* suffix */
            storageDir      /* directory */
        )
        // Save a file: path for using again
        cameraFilePath = image.absolutePath
        return image
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == CAMERA_REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK) {
                cameraFilePath?.let {
                    Glide.with(this)
                        .load(cameraFilePath)
                        .into(iv_camera)
                }

                activity?.sendBroadcast(
                    Intent(
                        Intent.ACTION_MEDIA_SCANNER_SCAN_FILE
                        , Uri.fromFile(File(cameraFilePath))
                    )
                )
            }
        }
    }

}
