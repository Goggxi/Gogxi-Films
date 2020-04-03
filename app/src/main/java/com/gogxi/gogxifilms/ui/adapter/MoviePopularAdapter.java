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
import com.gogxi.gogxifilms.ui.model.MoviePopularResult;

import java.util.ArrayList;

public class MoviePopularAdapter extends RecyclerView.Adapter<MoviePopularAdapter.ViewHolder> {

    private ArrayList<MoviePopularResult> moviePopularResults = new ArrayList<>();
    private Context context;

    private static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/";

    public MoviePopularAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<MoviePopularResult> items){
        moviePopularResults.clear();
        moviePopularResults.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MoviePopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviePopularAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(BASE_IMAGE_URL+moviePopularResults.get(position)
                .getPosterPath())
                .into(holder.imgPosterMovie);

        holder.tvTitleMovie.setText(moviePopularResults.get(position).getTitle());
        holder.tvRateMovie.setText(String.valueOf(moviePopularResults.get(position).getVoteAverage()));
    }

    @Override
    public int getItemCount() {
        return moviePopularResults.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPosterMovie;
        TextView tvTitleMovie, tvRateMovie;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPosterMovie = itemView.findViewById(R.id.img_poster_movie);
            tvTitleMovie = itemView.findViewById(R.id.tv_title_movie);
            tvRateMovie = itemView.findViewById(R.id.tv_rate_movie);
        }
    }
}
