package com.khairy.listing_list.presentation.view

import android.os.Bundle
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
import com.khairy.listing_list.domain.Product
import com.khairy.listing_list.presentation.adapters.ProductsAdapter
import com.khairy.listing_list.presentation.viewmodel.ProductsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class ProductsFragment : Fragment() {
    private val viewModel by viewModel<ProductsViewModel>()
    private lateinit var binding: FragmentListingListBinding
    private lateinit var adapter: ProductsAdapter
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
        viewModel.getListingsResultLD.observe(viewLifecycleOwner) {
            adapter.submitList(it.products ?: listOf())
        }

        viewModel.dataLoading.observe(viewLifecycleOwner) {
            binding.progressBarListingList.shouldShow(it)
        }
        viewModel.showErrorMessageEvent.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
        }
        viewModel.showNoNetworkScreenEvent.observe(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(),
                getString(R.string.grl_err_no_network),
                Toast.LENGTH_SHORT
            ).show()
        }
        viewModel.showServerIssueEvent.observe(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(),
                getString(R.string.grl_err_server_error),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun bindAdapter() {
        adapter = ProductsAdapter(object : ProductsAdapter.Interaction {
            override fun onItemSelected(item: Product) {
                findNavController().navigate(
                    R.id.action_listingListFragment_to_listingDetailsFragment,
                    bundleOf("listing" to Gson().toJson(item))
                )
            }
        })
        binding.rvListings.adapter = adapter
    }

}


