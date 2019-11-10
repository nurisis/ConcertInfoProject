package com.secondhands.navigationexamproject.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.secondhands.android.home.ui.BookMarkListAdapter

import com.secondhands.navigationexamproject.R
import com.secondhands.navigationexamproject.databinding.MainFragmentBinding
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val listViewModel by sharedViewModel<ListViewModel>()
    private lateinit var viewDataBinding : MainFragmentBinding

    private lateinit var bookMarkListAdapter: BookMarkListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = MainFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = listViewModel
            lifecycleOwner = this@MainFragment
        }

        bookMarkListAdapter = BookMarkListAdapter(listViewModel)
        viewDataBinding.rvBookmark.apply {
            adapter = bookMarkListAdapter
//            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            layoutManager = GridLayoutManager(activity, 2)
        }

        // Go to list fragment
        viewDataBinding.btnCta.setOnClickListener {
            Navigation.findNavController(viewDataBinding.root).navigate(R.id.action_global_frag_list)
        }

        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        listViewModel.bookMarkList.observe(viewLifecycleOwner, Observer {
            bookMarkListAdapter.submitList(it)
        })
    }

}
