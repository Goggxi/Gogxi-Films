package com.gogxi.gogxifilms.data.response;

import java.util.ArrayList;

import com.gogxi.gogxifilms.data.model.MoviePlayNow;
import com.google.gson.annotations.SerializedName;

public class MoviePlayNowResponse {

	@SerializedName("dates")
	private MoviePlayNowDates dates;

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private ArrayList<MoviePlayNow> results;

	@SerializedName("total_results")
	private int totalResults;

	public void setDates(MoviePlayNowDates dates){
		this.dates = dates;
	}

	public MoviePlayNowDates getDates(){
		return dates;
	}

	public void setPage(int page){
		this.page = page;
	}

	public int getPage(){
		return page;
	}

	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public void setResults(ArrayList<MoviePlayNow> results){
		this.results = results;
	}

	public ArrayList<MoviePlayNow> getResults(){
		return results;
	}

	public void setTotalResults(int totalResults){
		this.totalResults = totalResults;
	}

	public int getTotalResults(){
		return totalResults;
	}

	@Override
 	public String toString(){
		return 
			"MoviePlayNow{" + 
			"dates = '" + dates + '\'' + 
			",page = '" + page + '\'' + 
			",total_pages = '" + totalPages + '\'' + 
			",results = '" + results + '\'' + 
			",total_results = '" + totalResults + '\'' + 
			"}";
		}
}