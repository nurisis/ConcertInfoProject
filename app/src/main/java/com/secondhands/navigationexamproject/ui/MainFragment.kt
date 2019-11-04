package com.secondhands.navigationexamproject.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.secondhands.navigationexamproject.R
import com.secondhands.navigationexamproject.databinding.MainFragmentBinding
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var mainViewModel: MainViewModel
    private lateinit var viewDataBinding : MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewDataBinding = MainFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = mainViewModel
        }

        // Go to list fragment
        viewDataBinding.btnCta.setOnClickListener {
            Navigation.findNavController(viewDataBinding.root).navigate(R.id.action_global_frag_list)
        }

        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
