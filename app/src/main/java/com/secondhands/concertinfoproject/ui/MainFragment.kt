package com.secondhands.concertinfoproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.secondhands.android.home.ui.BookMarkListAdapter

import com.secondhands.concertinfoproject.R
import com.secondhands.concertinfoproject.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    // Share the viewModel created in the main activity
    private val concertViewModel by sharedViewModel<ConcertViewModel>()
    private lateinit var viewDataBinding : FragmentMainBinding
    private lateinit var bookMarkListAdapter: BookMarkListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = FragmentMainBinding.inflate(inflater, container, false).apply {
            viewmodel = concertViewModel
            lifecycleOwner = this@MainFragment
        }

        bookMarkListAdapter = BookMarkListAdapter(concertViewModel)
        viewDataBinding.rvBookmark.apply {
            adapter = bookMarkListAdapter
            layoutManager = GridLayoutManager(activity, 2)
        }

        // Go to concert list fragment
        viewDataBinding.btnCta.setOnClickListener {
            Navigation.findNavController(viewDataBinding.root).navigate(R.id.action_global_frag_list)
        }

        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Observe a list of user's bookmark
        concertViewModel.bookMarkList.observe(viewLifecycleOwner, Observer {
            bookMarkListAdapter.submitList(it)
        })
    }

}
