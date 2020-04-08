package com.gogxi.gogxifilms.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TVShow implements Parcelable {

	@SerializedName("first_air_date")
	private String firstAirDate;

	@SerializedName("overview")
	private String overview;

	@SerializedName("original_language")
	private String originalLanguage;

	@SerializedName("poster_path")
	private String posterPath;

	@SerializedName("backdrop_path")
	private String backdropPath;

	@SerializedName("vote_average")
	private double voteAverage;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	private TVShow(Parcel in) {
		firstAirDate = in.readString();
		overview = in.readString();
		originalLanguage = in.readString();
		posterPath = in.readString();
		backdropPath = in.readString();
		voteAverage = in.readDouble();
		name = in.readString();
		id = in.readInt();
	}

	public TVShow() {

	}

	public static final Creator<TVShow> CREATOR = new Creator<TVShow>() {
		@Override
		public TVShow createFromParcel(Parcel in) {
			return new TVShow(in);
		}

		@Override
		public TVShow[] newArray(int size) {
			return new TVShow[size];
		}
	};

	public void setFirstAirDate(String firstAirDate){
		this.firstAirDate = firstAirDate;
	}

	public String getFirstAirDate(){
		return firstAirDate;
	}

	public void setOverview(String overview){
		this.overview = overview;
	}

	public String getOverview(){
		return overview;
	}

	public void setOriginalLanguage(String originalLanguage){
		this.originalLanguage = originalLanguage;
	}

	public String getOriginalLanguage(){
		return originalLanguage;
	}

	public void setPosterPath(String posterPath){
		this.posterPath = posterPath;
	}

	public String getPosterPath(){
		return posterPath;
	}

	public void setBackdropPath(String backdropPath){
		this.backdropPath = backdropPath;
	}

	public String getBackdropPath(){
		return backdropPath;
	}

	public void setVoteAverage(double voteAverage){
		this.voteAverage = voteAverage;
	}

	public double getVoteAverage(){
		return voteAverage;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"ResultsItem{" + 
			"first_air_date = '" + firstAirDate + '\'' + 
			",overview = '" + overview + '\'' + 
			",original_language = '" + originalLanguage + '\'' +
			",poster_path = '" + posterPath + '\'' +
			",backdrop_path = '" + backdropPath + '\'' +
			",vote_average = '" + voteAverage + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' +
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(firstAirDate);
		dest.writeString(overview);
		dest.writeString(originalLanguage);
		dest.writeString(posterPath);
		dest.writeString(backdropPath);
		dest.writeDouble(voteAverage);
		dest.writeString(name);
		dest.writeInt(id);
	}
}