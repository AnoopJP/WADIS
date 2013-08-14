package com.phoenix.wdis.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.phoenix.wdis.R;
import com.phoenix.wdis.adapter.MovieListAdapter;
import com.phoenix.wdis.model.ShowDetails;
import com.phoenix.wdis.sqlite.dao.MovieDao;

public class MovieListActivity extends Activity {

	private ListView listView;
	private MovieDao movieDao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_list);

		listView = (ListView) findViewById(R.id.mov_list);

		MovieListAdapter adapter = new MovieListAdapter(MovieListActivity.this,
				R.layout.movie_list_item, getMovieList());
		listView.setAdapter(adapter);
	}

	private ArrayList<ShowDetails> getMovieList() {

		movieDao = new MovieDao(MovieListActivity.this);
		ArrayList<ShowDetails> movieList = new ArrayList<ShowDetails>();

		return movieList;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.movie_list, menu);
		return true;
	}

}
