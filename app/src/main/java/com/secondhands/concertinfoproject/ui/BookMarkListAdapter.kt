package com.secondhands.android.home.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.secondhands.concertinfoproject.R
import com.secondhands.concertinfoproject.databinding.ItemBookmarkListBinding
import com.secondhands.concertinfoproject.entity.ConcertItem
import com.secondhands.concertinfoproject.ui.ConcertViewModel

class BookMarkListAdapter(private val viewModel: ConcertViewModel) : PagedListAdapter<ConcertItem, BookMarkListAdapter.ViewHolder>(BookMarkDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, viewModel)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class ViewHolder private constructor(private val binding: ItemBookmarkListBinding, private val viewModel: ConcertViewModel) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(item: ConcertItem) {
            binding.item = item
            binding.executePendingBindings()
        }

        /**
         * When click a performance item from bookmark list,
         * Go to a detail page of item
         * */
        override fun onClick(v: View?) {
            binding.item?.let {
                viewModel.clickConcertItem(it)
                // Page navigation with the navController
                Navigation.findNavController(binding.root).navigate(R.id.action_frag_main_to_concertDetailFragment)
            }
        }

        companion object {
            fun from(parent: ViewGroup, viewModel: ConcertViewModel) : ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemBookmarkListBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding, viewModel)
            }
        }
    }
}

/**
 * Using DiffUtil to efficiently update the data in the list
 * */
class BookMarkDiffCallback : DiffUtil.ItemCallback<ConcertItem>() {
    override fun areItemsTheSame(oldItem: ConcertItem, newItem: ConcertItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: ConcertItem, newItem: ConcertItem): Boolean {
        return oldItem == newItem
    }
}