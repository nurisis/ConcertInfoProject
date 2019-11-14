package com.nurisis.concertinfoproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nurisis.concertinfoproject.databinding.FragmentConcertDetailBinding

import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ConcertDetailFragment : Fragment() {

    companion object {
        fun newInstance() = ConcertDetailFragment()
    }

    // Share the viewModel created in the main activity
    private val concertViewModel by sharedViewModel<ConcertViewModel>()
    private lateinit var viewDataBinding : FragmentConcertDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentConcertDetailBinding.inflate(inflater, container, false).apply {
            viewModel = concertViewModel
            lifecycleOwner = this@ConcertDetailFragment
        }

        // If there is a bundle object passed in during navigation, it can be used here.
        arguments?.let {
        }

        return viewDataBinding.root
    }
}
