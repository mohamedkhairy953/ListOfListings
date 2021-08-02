package com.khairy.listing_list.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.khairy.core.test_utils.EspressoIdlingResource
import com.khairy.listing_list.R
import com.khairy.listing_list.databinding.ListingItemBinding
import com.khairy.listing_list.domain.Listing

class ListingListAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Listing>() {

        override fun areItemsTheSame(oldItem: Listing, newItem: Listing): Boolean {
            return oldItem.uid == newItem.uid
        }

        override fun areContentsTheSame(oldItem: Listing, newItem: Listing): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ListingViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.listing_item,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ListingViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Listing>) {
        EspressoIdlingResource.increment()
        val dataCommitCallback = Runnable {
            EspressoIdlingResource.decrement()
        }
        differ.submitList(list, dataCommitCallback)
    }

    class ListingViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListingItemBinding.bind(itemView)

        fun bind(item: Listing) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(item)
            }
            Glide.with(binding.root).load(item.imageUrlsThumbs?.first())
                .into(binding.imageView)
            binding.tvName.text = item.name
            binding.tvPrice.text = item.price

        }
    }

    interface Interaction {
        fun onItemSelected(item: Listing)
    }
}