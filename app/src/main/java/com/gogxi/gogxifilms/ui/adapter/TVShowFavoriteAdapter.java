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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class TVShowFavoriteAdapter extends RecyclerView.Adapter<TVShowFavoriteAdapter.ViewHolder> {

    private ArrayList<TVShow> tvShow = new ArrayList<>();
    private Context context;

    private ArrayList<TVShow> getTv() {
        return tvShow;
    }

    private static final String BASE_IMAGE_URL = BuildConfig.POSTER ;

    public TVShowFavoriteAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<TVShow> items){
        tvShow.clear();
        tvShow.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TVShowFavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowFavoriteAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(BASE_IMAGE_URL+ tvShow.get(position)
                .getPosterPath())
                .into(holder.imgPoster);

        holder.tvTitle.setText(tvShow.get(position).getName());
        holder.getReleaseDate(tvShow.get(position));
        holder.tvRate.setText(String.valueOf(tvShow.get(position).getVoteAverage()));
        holder.tvLang.setText(tvShow.get(position).getOriginalLanguage());
    }

    @Override
    public int getItemCount() {
        return tvShow.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgPoster;
        TextView tvTitle, tvRelease, tvRate, tvLang;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.img_poster_favorite);
            tvTitle = itemView.findViewById(R.id.tv_title_favorite);
            tvRelease = itemView.findViewById(R.id.tv_release_favorite);
            tvRate = itemView.findViewById(R.id.tv_rate_favorite);
            tvLang = itemView.findViewById(R.id.tv_lang_favorite);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            TVShow tvShow = getTv().get(getAdapterPosition());
            Intent move = new Intent(itemView.getContext(), TvDetailActivity.class);
            move.putExtra(TvDetailActivity.EXTRA_TV, tvShow);
            v.getContext().startActivity(move);
        }

        private void getReleaseDate(TVShow tvShow) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            String date = tvShow.getFirstAirDate();
            try {
                Date newDate = dateFormat.parse(date);
                dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
                assert newDate != null;
                tvRelease.setText(dateFormat.format(newDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
