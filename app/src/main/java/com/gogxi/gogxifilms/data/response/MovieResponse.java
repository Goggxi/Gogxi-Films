package com.gogxi.gogxifilms.data.response;

import java.util.ArrayList;

import com.gogxi.gogxifilms.data.model.Movie;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class MovieResponse {

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private ArrayList<Movie> results;

	@SerializedName("total_results")
	private int totalResults;

	public MovieResponse(int page, int totalPages, ArrayList<Movie> results, int totalResults) {
		this.page = page;
		this.totalPages = totalPages;
		this.results = results;
		this.totalResults = totalResults;
	}

	public ArrayList<Movie> getResults(){
		return results;
	}

	@NotNull
	@Override
 	public String toString(){
		return 
			"MoviePopular{" + 
			"page = '" + page + '\'' + 
			",total_pages = '" + totalPages + '\'' + 
			",results = '" + results + '\'' + 
			",total_results = '" + totalResults + '\'' + 
			"}";
		}
}