package com.secondhands.navigationexamproject.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration

import com.secondhands.navigationexamproject.R
import com.secondhands.navigationexamproject.databinding.ListFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    private val listViewModel: ListViewModel by viewModel()
    private lateinit var viewDataBinding : ListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        listViewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewDataBinding = ListFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = listViewModel
        }

//        val navController =  Navigation.findNavController(viewDataBinding.root)
//        val appBarConfiguration = AppBarConfiguration(navController.graph)

        viewDataBinding.btnCta.setOnClickListener {
//            navController.navigate(R.id.action_global_frag_detail)
        }

        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewDataBinding.lifecycleOwner = viewLifecycleOwner

        listViewModel.concertResponse.observe(this, Observer {
            Log.d("LOG>>", it.toString())
        })

        listViewModel.toastText.observe(this, Observer {
            Toast.makeText(activity!!, it, Toast.LENGTH_LONG).show()
        })

        listViewModel.loadConcerts()
    }

}
