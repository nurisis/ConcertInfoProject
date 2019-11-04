//package com.secondhands.android.home.ui
//
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.navigation.Navigation
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.secondhands.navigationexamproject.R
//import com.secondhands.navigationexamproject.databinding.ItemConcertListBinding
//import com.secondhands.navigationexamproject.entity.ConcertItem
//import com.secondhands.navigationexamproject.ui.ListViewModel
//
//class ConcertListAdapter_bak(private val viewModel: ListViewModel) : ListAdapter<ConcertItem, ConcertListAdapter.ViewHolder>(MyPageDiffCallback()) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder.from(parent, viewModel)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = getItem(position)
//        holder.bind(item)
//    }
//
//    class ViewHolder private constructor(val binding: ItemConcertListBinding, private val viewModel: ListViewModel) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
//        init {
//            binding.root.setOnClickListener(this)
//        }
//
//        fun bind(item: ConcertItem) {
//            binding.item = item
//            binding.executePendingBindings()
//        }
//
//        override fun onClick(v: View?) {
//            binding.item?.let {
//                val bundle = Bundle()
//                bundle.putSerializable("item", binding.item!!)
//                Navigation.findNavController(binding.root).navigate(R.id.action_frag_list_to_concertDetailFragment, bundle)
//            }
//            Log.d("LOG>>", "Item Clicked !!")
//        }
//
//        companion object {
//            fun from(parent: ViewGroup, viewModel: ListViewModel) : ViewHolder {
//                val layoutInflater = LayoutInflater.from(parent.context)
//                val binding = ItemConcertListBinding.inflate(layoutInflater, parent, false)
//
//                return ViewHolder(binding, viewModel)
//            }
//        }
//    }
//}
//
//class MyPageDiffCallback : DiffUtil.ItemCallback<ConcertItem>() {
//    override fun areItemsTheSame(oldItem: ConcertItem, newItem: ConcertItem): Boolean {
//        return oldItem.title == newItem.title
//    }
//
//    override fun areContentsTheSame(oldItem: ConcertItem, newItem: ConcertItem): Boolean {
//        return oldItem == newItem
//    }
//}