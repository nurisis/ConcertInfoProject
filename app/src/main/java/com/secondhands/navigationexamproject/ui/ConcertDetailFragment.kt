package com.secondhands.navigationexamproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.secondhands.android.home.ui.ConcertListAdapter
import com.secondhands.navigationexamproject.databinding.FragmentConcertDetailBinding

import com.secondhands.navigationexamproject.databinding.FragmentConcertListBinding
import com.secondhands.navigationexamproject.entity.ConcertItem
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConcertDetailFragment : Fragment() {

    companion object {
        fun newInstance() = ConcertDetailFragment()
    }

    private lateinit var viewDataBinding : FragmentConcertDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentConcertDetailBinding.inflate(inflater, container, false)

        arguments?.let {
            viewDataBinding.item = it.getSerializable("item") as ConcertItem
        }

        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewDataBinding.lifecycleOwner = viewLifecycleOwner

    }

}
