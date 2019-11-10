package com.secondhands.navigationexamproject.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.secondhands.navigationexamproject.databinding.FragmentConcertDetailBinding

import com.secondhands.navigationexamproject.entity.ConcertItem
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ConcertDetailFragment : Fragment() {

    companion object {
        fun newInstance() = ConcertDetailFragment()
    }

    private val listViewModel by sharedViewModel<ListViewModel>()
    private lateinit var viewDataBinding : FragmentConcertDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentConcertDetailBinding.inflate(inflater, container, false).apply {
            viewModel = listViewModel
            lifecycleOwner = this@ConcertDetailFragment
        }

        arguments?.let {
        }

        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
