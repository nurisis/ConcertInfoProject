package com.secondhands.navigationexamproject.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.secondhands.android.home.ui.ConcertListAdapter

import com.secondhands.navigationexamproject.databinding.FragmentConcertListBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    private val listViewModel by sharedViewModel<ListViewModel>()
    private lateinit var listAdapter: ConcertListAdapter
    private lateinit var viewDataBinding : FragmentConcertListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = FragmentConcertListBinding.inflate(inflater, container, false).apply {
            viewmodel = listViewModel
            lifecycleOwner = this@ListFragment
        }

        listAdapter = ConcertListAdapter(listViewModel)
        viewDataBinding.rvConcerts.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }

        viewDataBinding.scrollView.viewTreeObserver.addOnScrollChangedListener {
            val positionOfScroll = viewDataBinding.scrollView!!.scrollY
            val last_position = viewDataBinding.scrollView!!.getChildAt(0).bottom - viewDataBinding.scrollView!!.height

            if(positionOfScroll == last_position) {
                Log.d("LOG>>","Bottom !!!")
            }
        }

        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        listViewModel.concertList.observe(viewLifecycleOwner, Observer {
            listAdapter.submitList(it)
        })

    }


}
