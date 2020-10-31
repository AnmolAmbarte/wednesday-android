package com.sample.wednesday.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sample.wednesday.R;
import com.sample.wednesday.model.Data;
import com.sample.wednesday.model.Details;

import java.util.ArrayList;
import java.util.List;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityViewHolder> {

    Context mContext;
    List<Data> mDetails = new ArrayList<Data>();

    public MainActivityAdapter(Context context, List<Data> data) {
        this.mContext = context;
        this.mDetails = data;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public MainActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_layout, parent, false);
        MainActivityViewHolder myViewHolder = new MainActivityViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainActivityViewHolder holder, int position) {
        String firstName = mDetails.get(position).getFirst_name();
        String lastName = mDetails.get(position).getLast_name();
        holder.tvTrackName.setText(firstName + " " + lastName);
        holder.tvCollectionName.setText(mDetails.get(position).getEmail());
        Glide.with(mContext)
                .load(mDetails.get(position).getAvatar())
                .placeholder(R.drawable.bed)
                .circleCrop()
                .into(holder.ivImage);
        //holder.tvName.setText(mDetails.get(position).getName());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (mDetails != null)
            return mDetails.size();
        else
            return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}
