package com.khairy.listing_list.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.khairy.listing_list.R
import com.khairy.listing_list.databinding.ListingItemBinding
import com.khairy.listing_list.domain.Listing

class ListingListAdapter(
    private val list: List<Listing>,
    private val onClick: (item: Listing) -> Unit
) :
    RecyclerView.Adapter<ListingListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingListViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.listing_item, parent, false)
        return ListingListViewHolder(v)
    }

    override fun onBindViewHolder(holder: ListingListViewHolder, position: Int) {
        val item = list[position]
        Glide.with(holder.binding.root).load(item.imageUrlsThumbs?.first())
            .into(holder.binding.imageView)
        holder.binding.tvName.text = item.name
        holder.binding.tvPrice.text = item.price
        holder.binding.root.setOnClickListener {
            onClick(item)
        }
    }

    override fun getItemCount() = list.size
}

class ListingListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = ListingItemBinding.bind(itemView)
}
