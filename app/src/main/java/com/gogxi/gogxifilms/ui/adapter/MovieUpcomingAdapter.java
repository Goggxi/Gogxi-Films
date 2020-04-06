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

public class MovieUpcomingAdapter extends RecyclerView.Adapter<MovieUpcomingAdapter.ViewHolder> {

    private ArrayList<Movie> movieUpcoming = new ArrayList<>();
    private Context context;

    private ArrayList<Movie> getMovies() {
        return movieUpcoming;
    }

    private static final String BASE_IMAGE_URL = BuildConfig.POSTER ;

    public MovieUpcomingAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<Movie> items){
        movieUpcoming.clear();
        movieUpcoming.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieUpcomingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieUpcomingAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(BASE_IMAGE_URL+movieUpcoming.get(position)
                .getPosterPath())
                .into(holder.imgPosterMovie);

        holder.tvTitleMovie.setText(movieUpcoming.get(position).getTitle());
        holder.tvRateMovie.setText(String.valueOf(movieUpcoming.get(position).getVoteAverage()));
    }

    @Override
    public int getItemCount() {
        return movieUpcoming.size();
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
