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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MovieFavoriteAdapter extends RecyclerView.Adapter<MovieFavoriteAdapter.ViewHolder> {

    private ArrayList<Movie> movie = new ArrayList<>();
    private Context context;

    private ArrayList<Movie> getMovies() {
        return movie;
    }

    private static final String BASE_IMAGE_URL = BuildConfig.POSTER ;

    public MovieFavoriteAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<Movie> items){
        movie.clear();
        movie.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieFavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieFavoriteAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(BASE_IMAGE_URL+ movie.get(position)
                .getPosterPath())
                .into(holder.imgPosterMovie);

        holder.tvTitleMovie.setText(movie.get(position).getTitle());
      //  holder.tvReleaseMovie.setText(movie.get(position).getReleaseDate());
        holder.getReleaseDate(movie.get(position));
        holder.tvRateMovie.setText(String.valueOf(movie.get(position).getVoteAverage()));
        holder.tvLangMovie.setText(movie.get(position).getOriginalLanguage());
    }

    @Override
    public int getItemCount() {
        return movie.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgPosterMovie;
        TextView tvTitleMovie, tvReleaseMovie, tvRateMovie, tvLangMovie;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPosterMovie = itemView.findViewById(R.id.img_poster_favorite);
            tvTitleMovie = itemView.findViewById(R.id.tv_title_favorite);
            tvReleaseMovie = itemView.findViewById(R.id.tv_release_favorite);
            tvRateMovie = itemView.findViewById(R.id.tv_rate_favorite);
            tvLangMovie = itemView.findViewById(R.id.tv_lang_favorite);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Movie movie = getMovies().get(getAdapterPosition());
            Intent moveMovie = new Intent(itemView.getContext(), MovieDetailActivity.class);
            moveMovie.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie);
            v.getContext().startActivity(moveMovie);
        }

        private void getReleaseDate(Movie movie) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            String date = movie.getReleaseDate();
            try {
                Date newDate = dateFormat.parse(date);
                dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
                assert newDate != null;
                tvReleaseMovie.setText(dateFormat.format(newDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
