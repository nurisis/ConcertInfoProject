package com.secondhands.navigationexamproject.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration

import com.secondhands.navigationexamproject.R
import com.secondhands.navigationexamproject.databinding.ListFragmentBinding

class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    private lateinit var listViewModel: ListViewModel
    private lateinit var viewDataBinding : ListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        listViewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewDataBinding = ListFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = listViewModel
        }

        val navController =  Navigation.findNavController(viewDataBinding.root)
//        val appBarConfiguration = AppBarConfiguration(navController.graph)

        viewDataBinding.btnCta.setOnClickListener {
            navController.navigate(R.id.action_global_frag_detail)
        }

        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listViewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
