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
import com.gogxi.gogxifilms.data.model.MoviePlayNow;
import com.gogxi.gogxifilms.data.model.MoviePopular;

import java.util.ArrayList;

public class MovieNowAdapter extends RecyclerView.Adapter<MovieNowAdapter.ViewHolder> {

    private ArrayList<MoviePlayNow> moviePlayNows = new ArrayList<>();
    private Context context;

    private static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/";

    public MovieNowAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<MoviePlayNow> items){
        moviePlayNows.clear();
        moviePlayNows.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieNowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.now_movie_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieNowAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(BASE_IMAGE_URL+moviePlayNows.get(position)
                .getPosterPath())
                .into(holder.imgPosterMovie);

        holder.tvTitleMovie.setText(moviePlayNows.get(position).getTitle());
        holder.tvRateMovie.setText(String.valueOf(moviePlayNows.get(position).getVoteAverage()));
    }

    @Override
    public int getItemCount() {
        return moviePlayNows.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPosterMovie;
        TextView tvTitleMovie, tvRateMovie;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPosterMovie = itemView.findViewById(R.id.img_poster_now);
            tvTitleMovie = itemView.findViewById(R.id.tv_title_now);
            tvRateMovie = itemView.findViewById(R.id.tv_rate_now);
        }
    }
}
