package com.phoenix.wdis.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.phoenix.wdis.R;
import com.phoenix.wdis.util.Utility;

public class AddMovieActivity extends Activity {

	private Button saveButton;
	private EditText movieName;
	private EditText movieReview;
	private int movieRating;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_movie);

		initViews();
		setViews();
	}

	private void setViews() {
		saveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Utility.showToast("test", AddMovieActivity.this);
			}
		});
	}

	private void initViews() {
		saveButton = (Button) findViewById(R.id.save_button);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_movie, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
			case android.R.id.home:
				Intent intent = new Intent(this, MovieListActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				break;
		}
		return true;
	}
}
