package com.secondhands.android.home.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.secondhands.navigationexamproject.databinding.ItemBookmarkListBinding
import com.secondhands.navigationexamproject.entity.ConcertItem
import com.secondhands.navigationexamproject.ui.ListViewModel

class BookMarkListAdapter(private val viewModel: ListViewModel) : PagedListAdapter<ConcertItem, BookMarkListAdapter.ViewHolder>(BookMarkDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, viewModel)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class ViewHolder private constructor(val binding: ItemBookmarkListBinding, private val viewModel: ListViewModel) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(item: ConcertItem) {
            binding.item = item
            binding.executePendingBindings()
        }

        override fun onClick(v: View?) {
//            binding.item?.let {
//                val bundle = Bundle()
//                Navigation.findNavController(binding.root).navigate(R.id.action_frag_list_to_concertDetailFragment, bundle)
//            }
        }

        companion object {
            fun from(parent: ViewGroup, viewModel: ListViewModel) : ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemBookmarkListBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding, viewModel)
            }
        }
    }
}

class BookMarkDiffCallback : DiffUtil.ItemCallback<ConcertItem>() {
    override fun areItemsTheSame(oldItem: ConcertItem, newItem: ConcertItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: ConcertItem, newItem: ConcertItem): Boolean {
        return oldItem == newItem
    }
}