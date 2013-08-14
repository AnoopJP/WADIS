package com.phoenix.wdis.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.phoenix.wdis.R;
import com.phoenix.wdis.adapter.MovieListAdapter;
import com.phoenix.wdis.model.ShowDetails;
import com.phoenix.wdis.sqlite.dao.MovieDao;
import com.phoenix.wdis.util.Utility;

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
		ArrayList<ShowDetails> movieList = (ArrayList<ShowDetails>) movieDao
				.getAllMovies();

		return movieList;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.movie_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.action_settings:
				Toast.makeText(this, "Menu Item 1 selected", Toast.LENGTH_SHORT)
						.show();
				break;
			case R.id.action_refresh:
				Toast.makeText(this, "Menu item 2 selected", Toast.LENGTH_SHORT)
						.show();
				break;
			case R.id.action_add:

				startActivityForResult(new Intent(MovieListActivity.this,
						AddMovieActivity.class), 1);
				break;

			default:
				break;
		}

		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {
				Utility.showToast("I'm back :)", MovieListActivity.this);
			} else {
				Utility.showToast("I'm not back :(", MovieListActivity.this);
			}
		} else {
			Utility.showToast("wha?", MovieListActivity.this);
		}
	}
}
