package com.phoenix.wdis.sqlite.dao;

import java.util.List;

import com.phoenix.wdis.model.ShowDetails;

public interface MovieDao {

	public void insertMovie(ShowDetails movie);

	public int updateMovie(ShowDetails movie);

	public void deleteMovie(ShowDetails movie);

	public List<ShowDetails> getAllMovies();

	public ShowDetails getMovie(String movieName);
}
