package com.secondhands.navigationexamproject.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.secondhands.navigationexamproject.R
import com.secondhands.navigationexamproject.databinding.DetailFragmentBinding

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var viewDataBinding : DetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewDataBinding = DetailFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = detailViewModel
        }


        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
