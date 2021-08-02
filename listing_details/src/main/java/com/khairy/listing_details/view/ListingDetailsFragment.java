package com.khairy.listing_details.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.khairy.listing_details.R;
import com.khairy.listing_details.model.Listing;

import java.util.ArrayList;


public class ListingDetailsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_listing_details, container, false);
        Listing listing = getListingModel();
        if (listing != null) {
            bindData(v, listing);
        } else {
            Toast.makeText(requireContext(), "Data Null", Toast.LENGTH_SHORT).show();
        }
        return v;
    }

    private void bindData(View v, Listing listing) {
        ImageView imageView = v.findViewById(R.id.imageView);
        bindImageView(imageView, listing.getImageUrls());
        TextView tvName = v.findViewById(R.id.tv_name);
        tvName.setText(listing.getName());
        TextView tvPrice = v.findViewById(R.id.tv_price);
        tvPrice.setText(listing.getPrice());
    }

    private void bindImageView(ImageView imageView, ArrayList<String> ivs) {
        if (ivs.size() > 0)
            Glide.with(requireContext()).load(ivs.get(0)).into(imageView);
        else
            imageView.setVisibility(View.GONE);

    }

    private Listing getListingModel() {
        if (getArguments() != null) {
            String s = getArguments().getString("listing");
            return new Gson().fromJson(s, Listing.class);
        }
        return null;
    }


}
