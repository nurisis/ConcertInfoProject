package com.nurisis.android.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nurisis.concertinfoproject.R
import com.nurisis.concertinfoproject.databinding.ItemConcertListBinding
import com.nurisis.concertinfoproject.entity.ConcertItem
import com.nurisis.concertinfoproject.ui.ConcertViewModel

class ConcertListAdapter(private val viewModel: ConcertViewModel) : ListAdapter<ConcertItem, ConcertListAdapter.ViewHolder>(MyPageDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, viewModel)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(val binding: ItemConcertListBinding, private val viewModel: ConcertViewModel) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(item: ConcertItem) {
            binding.item = item
            binding.executePendingBindings()
        }

        /**
         * When click a performance item from performance list,
         * Go to a detail page of item
         * */
        override fun onClick(v: View?) {
            binding.item?.let {
                viewModel.clickConcertItem(it)

                // If you want to pass data when navigating, Use a Bundle like below.
                val bundle = Bundle()
//                bundle.putSerializable("item", binding.item!!)

                // Page navigation with the navController
                Navigation.findNavController(binding.root).navigate(R.id.action_frag_list_to_concertDetailFragment, bundle)
            }
        }

        companion object {
            fun from(parent: ViewGroup, viewModel: ConcertViewModel) : ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemConcertListBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding, viewModel)
            }
        }
    }
}

class MyPageDiffCallback : DiffUtil.ItemCallback<ConcertItem>() {
    override fun areItemsTheSame(oldItem: ConcertItem, newItem: ConcertItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: ConcertItem, newItem: ConcertItem): Boolean {
        return oldItem == newItem
    }
}