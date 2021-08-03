package com.khairy.listing_details.view;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
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
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.khairy.bit_cacher.BitmapCacher;
import com.khairy.listing_details.R;
import com.khairy.listing_details.model.Listing;


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
        bindImageView(imageView, listing);
        TextView tvName = v.findViewById(R.id.tv_name);
        tvName.setText(listing.getName());
        TextView tvPrice = v.findViewById(R.id.tv_price);
        tvPrice.setText(listing.getPrice());
    }

    private void bindImageView(ImageView imageView, Listing item) {
        if (item.getImageIds().size() > 0) {
            String imageKey = item.getImageIds().get(0);
            Bitmap bitmapFromCache = BitmapCacher.INSTANCE.getBitmap(imageKey);
            if (bitmapFromCache != null)
                imageView.setImageBitmap(bitmapFromCache);
            else {
                if (item.getImageUrls().size() > 0)
                    bindWithGlide(imageKey, item.getImageUrls().get(0), imageView);

            }
        }


    }

    private Listing getListingModel() {
        if (getArguments() != null) {
            String s = getArguments().getString("listing");
            return new Gson().fromJson(s, Listing.class);
        }
        return null;
    }

    private void bindWithGlide(String key, String url, ImageView imageView) {
        Glide.with(this).asBitmap().load(url)
                .into(new CustomTarget<Bitmap>() {

                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        imageView.setImageBitmap(resource);
                        BitmapCacher.INSTANCE.saveBitmap(key, resource);

                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });
    }

}
