package com.nurisis.concertinfoproject.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nurisis.android.home.ui.ConcertListAdapter

import com.nurisis.concertinfoproject.databinding.FragmentConcertListBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ConcertListFragment : Fragment() {

    companion object {
        fun newInstance() = ConcertListFragment()
    }

    // Share the viewModel created in the main activity
    private val concertViewModel by sharedViewModel<ConcertViewModel>()
    private lateinit var listAdapter: ConcertListAdapter
    private lateinit var viewDataBinding : FragmentConcertListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("LOG>>", "${(concertViewModel == null)}")
        viewDataBinding = FragmentConcertListBinding.inflate(inflater, container, false).apply {
            viewmodel = concertViewModel
            lifecycleOwner = this@ConcertListFragment
        }

        listAdapter = ConcertListAdapter(concertViewModel)
        viewDataBinding.rvConcerts.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }

        // Listener of scrollview, To paging the list when the list touches the floor
        viewDataBinding.scrollView.viewTreeObserver.addOnScrollChangedListener {
            val positionOfScroll = viewDataBinding.scrollView!!.scrollY
            val last_position = viewDataBinding.scrollView!!.getChildAt(0).bottom - viewDataBinding.scrollView!!.height

            if(positionOfScroll == last_position) {
                // Call the next part of the list
                // I do not handle a paging of the list here :)
            }
        }

        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Observe a list of performances
        concertViewModel.concertList.observe(viewLifecycleOwner, Observer {
            listAdapter.submitList(it)
        })

    }


}
