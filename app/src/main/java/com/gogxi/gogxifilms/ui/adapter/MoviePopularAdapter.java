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
import com.gogxi.gogxifilms.data.model.Movie;
import com.gogxi.gogxifilms.ui.activity.MovieDetailActivity;

import java.util.ArrayList;

public class MoviePopularAdapter extends RecyclerView.Adapter<MoviePopularAdapter.ViewHolder> {

    private ArrayList<Movie> movie = new ArrayList<>();
    private Context context;

    public ArrayList<Movie> getMovies() {
        return movie;
    }

    private static final String BASE_IMAGE_URL = BuildConfig.POSTER ;

    public MoviePopularAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<Movie> items){
        movie.clear();
        movie.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MoviePopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviePopularAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(BASE_IMAGE_URL+ movie.get(position)
                .getPosterPath())
                .into(holder.imgPosterMovie);

        holder.tvTitleMovie.setText(movie.get(position).getTitle());
        holder.tvRateMovie.setText(String.valueOf(movie.get(position).getVoteAverage()));
    }

    @Override
    public int getItemCount() {
        return movie.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgPosterMovie;
        TextView tvTitleMovie, tvRateMovie;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPosterMovie = itemView.findViewById(R.id.img_poster_items);
            tvTitleMovie = itemView.findViewById(R.id.tv_title_items);
            tvRateMovie = itemView.findViewById(R.id.tv_rate_items);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Movie movie = getMovies().get(getAdapterPosition());
            Intent moveMovie = new Intent(itemView.getContext(), MovieDetailActivity.class);
            moveMovie.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie);
            v.getContext().startActivity(moveMovie);
        }
    }
}
