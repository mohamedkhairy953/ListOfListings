package com.khairy.listing_list.presentation.view

import android.net.TrafficStats
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.khairy.core.helpers.extensions.shouldShow
import com.khairy.listing_list.R
import com.khairy.listing_list.databinding.FragmentListingListBinding
import com.khairy.listing_list.domain.Listing
import com.khairy.listing_list.presentation.adapters.ListingListAdapter
import com.khairy.listing_list.presentation.viewmodel.ListingListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class ListingListFragment : Fragment() {
    private val viewModel by viewModel<ListingListViewModel>()
    private lateinit var binding: FragmentListingListBinding
    private lateinit var adapter: ListingListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListingListBinding.bind(
            inflater.inflate(R.layout.fragment_listing_list, container, false)
        )
        observeViewModel()
        bindAdapter()
        return binding.root
    }

    private fun observeViewModel() {
        viewModel.getListingsResultLD.observe(viewLifecycleOwner, {
            Log.d("TAG", "observeViewModel: ${TrafficStats.getTotalRxBytes()/(1024*1024 )}")
            adapter.submitList(it.lisitngs ?: listOf())
        })

        viewModel.dataLoading.observe(viewLifecycleOwner, {
           binding.progressBarListingList.shouldShow(it)
        })
        viewModel.showErrorMessageEvent.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
        })
        viewModel.showNoNetworkScreenEvent.observe(viewLifecycleOwner, {
            Toast.makeText(
                requireContext(),
                getString(R.string.grl_err_no_network),
                Toast.LENGTH_SHORT
            ).show()
        })
        viewModel.showServerIssueEvent.observe(viewLifecycleOwner, {
            Toast.makeText(
                requireContext(),
                getString(R.string.grl_err_server_error),
                Toast.LENGTH_SHORT
            ).show()
        })
    }

    private fun bindAdapter() {
        adapter = ListingListAdapter(object : ListingListAdapter.Interaction {
            override fun onItemSelected(item: Listing) {
                findNavController().navigate(
                    R.id.action_listingListFragment_to_listingDetailsFragment,
                    bundleOf("listing" to Gson().toJson(item))
                )
            }
        })
        binding.rvListings.adapter = adapter
    }

}


