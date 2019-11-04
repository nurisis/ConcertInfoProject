package com.secondhands.android.util

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imagePath", "pathError", "imageOption")
fun loadImage(imageView: ImageView, @Nullable path:String?, errorDrawable: Drawable, option:String) {
    var myOptions = RequestOptions()
        .placeholder(errorDrawable)
        .error(errorDrawable)


    when(option) {
        "fit" -> myOptions = myOptions.fitCenter()
        "inside" -> myOptions = myOptions.centerInside()
        "crop" -> myOptions = myOptions.centerCrop()
    }

    Glide.with(imageView.context)
        .load(path ?: "")
        .apply(myOptions)
        .into(imageView)
}

@BindingAdapter("date")
fun TextView.convertDate(rawDate:String) {
    text = rawDate.substring(0,4) + "/" + rawDate.substring(4,6) + "/" + rawDate.substring(6)
}

@BindingConversion
fun convertBooleanToVisibility(visible:Boolean) :Int {
    return if(visible) View.VISIBLE else View.GONE
}
