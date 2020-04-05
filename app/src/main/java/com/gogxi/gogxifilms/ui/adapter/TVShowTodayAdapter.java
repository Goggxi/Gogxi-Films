package com.gogxi.gogxifilms.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gogxi.gogxifilms.R;
import com.gogxi.gogxifilms.data.model.TVShow;

import java.util.ArrayList;

public class TVShowTodayAdapter extends RecyclerView.Adapter<TVShowTodayAdapter.ViewHolder> {

    private ArrayList<TVShow> tvShows = new ArrayList<>();
    private Context context;

    private static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/";



    public TVShowTodayAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<TVShow> items){
        tvShows.clear();
        tvShows.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TVShowTodayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tv,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowTodayAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(BASE_IMAGE_URL+tvShows.get(position)
                .getPosterPath())
                .into(holder.imgPoster);

        holder.tvTitle.setText(tvShows.get(position).getName());
        holder.tvRate.setText(String.valueOf(tvShows.get(position).getVoteAverage()));
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPoster;
        TextView tvTitle, tvRate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.img_poster_item_tv);
            tvTitle = itemView.findViewById(R.id.tv_title_item_tv);
            tvRate = itemView.findViewById(R.id.tv_rate_item_tv);
        }
    }
}
