package com.secondhands.navigationexamproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.secondhands.android.home.ui.ConcertListAdapter

import com.secondhands.navigationexamproject.databinding.FragmentConcertListBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    private val listViewModel: ListViewModel by viewModel()
    private val listAdapter: ConcertListAdapter by inject()
    private lateinit var viewDataBinding : FragmentConcertListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = FragmentConcertListBinding.inflate(inflater, container, false).apply {
            viewmodel = listViewModel
        }

        viewDataBinding.rvConcerts.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }


        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewDataBinding.lifecycleOwner = viewLifecycleOwner

        listViewModel.conCertLiveData.observe(viewLifecycleOwner, Observer {
            listAdapter.submitList(it)
        })

        listViewModel.toastText.observe(this, Observer {
            Toast.makeText(activity!!, it, Toast.LENGTH_LONG).show()
        })

    }

}
