package com.gogxi.gogxifilms.data.response;

import java.util.ArrayList;


import com.gogxi.gogxifilms.data.model.TVShow;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class TVShowResponse{

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private ArrayList<TVShow> results;

	@SerializedName("total_results")
	private int totalResults;

	public TVShowResponse(int page, int totalPages, ArrayList<TVShow> results, int totalResults) {
		this.page = page;
		this.totalPages = totalPages;
		this.results = results;
		this.totalResults = totalResults;
	}


	public ArrayList<TVShow> getResults(){
		return results;
	}

	@NotNull
	@Override
 	public String toString(){
		return 
			"TVShowResponse{" + 
			"page = '" + page + '\'' + 
			",total_pages = '" + totalPages + '\'' + 
			",results = '" + results + '\'' + 
			",total_results = '" + totalResults + '\'' + 
			"}";
		}
}