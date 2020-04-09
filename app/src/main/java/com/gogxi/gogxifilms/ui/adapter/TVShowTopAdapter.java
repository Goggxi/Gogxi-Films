package com.gogxi.gogxifilms.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gogxi.gogxifilms.BuildConfig;
import com.gogxi.gogxifilms.R;
import com.gogxi.gogxifilms.data.model.TVShow;
import com.gogxi.gogxifilms.ui.activity.TvDetailActivity;

import java.util.ArrayList;

public class TVShowTopAdapter extends RecyclerView.Adapter<TVShowTopAdapter.ViewHolder> {

    private ArrayList<TVShow> tvShows = new ArrayList<>();
    private Context context;

    private ArrayList<TVShow> getTv() {
        return tvShows;
    }

    private static final String BASE_IMAGE_URL = BuildConfig.POSTER ;
    public TVShowTopAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<TVShow> items){
        tvShows.clear();
        tvShows.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TVShowTopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowTopAdapter.ViewHolder holder, int position) {
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgPoster;
        TextView tvTitle, tvRate;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.img_poster_items);
            tvTitle = itemView.findViewById(R.id.tv_title_items);
            tvRate = itemView.findViewById(R.id.tv_rate_items);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            TVShow tv = getTv().get(getAdapterPosition());
            Intent moveTv = new Intent(itemView.getContext(), TvDetailActivity.class);
            moveTv.putExtra(TvDetailActivity.EXTRA_TV, tv);
            v.getContext().startActivity(moveTv);
        }
    }
}
