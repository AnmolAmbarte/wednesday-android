package com.sample.wednesday.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sample.wednesday.R;

public class MainActivityViewHolder extends RecyclerView.ViewHolder {

    ImageView ivImage;
    TextView tvTrackName;
    TextView tvCollectionName;
    TextView tvArtistName;

    public MainActivityViewHolder(@NonNull View itemView) {
        super(itemView);
        ivImage = itemView.findViewById(R.id.img_image);
        tvTrackName = itemView.findViewById(R.id.tv_track_name);
        tvCollectionName = itemView.findViewById(R.id.tv_collection_name);
        tvArtistName = itemView.findViewById(R.id.tv_artist_name);

    }
}
