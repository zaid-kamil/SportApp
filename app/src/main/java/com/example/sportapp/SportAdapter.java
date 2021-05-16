package com.example.sportapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.ViewHolder> {

    private Context ctx;
    private List<Sport> mSportsData;

    public SportAdapter(Context ctx, List<Sport> mSportsData) {
        this.ctx = ctx;
        this.mSportsData = mSportsData;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull SportAdapter.ViewHolder holder, int position) {
        Sport currSport = mSportsData.get(position);
        holder.bindTo(currSport);
    }

    @Override
    public int getItemCount() {
        return mSportsData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final SportAdapter adapter;
        TextView textTitle;
        TextView textSummary;
        ImageView imgSport;

        public ViewHolder(@NonNull View itemView, SportAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            textSummary = itemView.findViewById(R.id.textSummary);
            textTitle = itemView.findViewById(R.id.textTitle);
            imgSport = itemView.findViewById(R.id.img_sport);
        }

        public void bindTo(Sport currSport) {
            textTitle.setText(currSport.title);
            textSummary.setText(currSport.summary);
            Glide.with(ctx).load(currSport.image).into(imgSport);
        }
    }
}
