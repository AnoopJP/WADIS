package com.phoenix.wdis.sqlite.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.phoenix.wdis.model.ShowDetails;
import com.phoenix.wdis.sqlite.DatabaseHelper;
import com.phoenix.wdis.sqlite.RowMapper;
import com.phoenix.wdis.util.Constants;

public class MovieDao extends DatabaseHelper {

	public MovieDao(Context context) {
		super(context);
	}

	public void insertMovie(ShowDetails movie) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(Constants.KEY_NAME, movie.getShowName());
		values.put(Constants.KEY_RATING, movie.getRating());
		values.put(Constants.KEY_UPDATED_DATE, "" + new Date().getTime());

		// Inserting Row
		db.insert(TABLE_MOVIES, null, values);
		db.close(); // Closing database connection
	}

	public void deleteMovie(ShowDetails movie) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_MOVIES, Constants.KEY_ID + " = ?",
				new String[] { String.valueOf(movie.getId()) });
		db.close();
	}

	public List<ShowDetails> getAllMovies() {

		List<ShowDetails> contactList = new ArrayList<ShowDetails>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_MOVIES;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				ShowDetails contact = RowMapper.MapRowToShowDetails(cursor);
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		// return contact list
		return contactList;
	}

	public ShowDetails getMovie(String movieName) {

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_MOVIES, new String[] { Constants.KEY_ID,
				Constants.KEY_NAME, Constants.KEY_RATING }, Constants.KEY_NAME
				+ "=?", new String[] { movieName }, null, null,
				Constants.KEY_ID, null);

		if (cursor != null) {
			cursor.moveToFirst();
		}

		ShowDetails contact = RowMapper.MapRowToShowDetails(cursor);
		db.close();
		return contact;
	}

	public int updateMovie(ShowDetails movie) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(Constants.KEY_NAME, movie.getShowName());
		values.put(Constants.KEY_RATING, movie.getRating());
		values.put(Constants.KEY_UPDATED_DATE, new Date().getTime());

		// updating row
		int rows = db.update(TABLE_MOVIES, values, Constants.KEY_ID + " = ?",
				new String[] { String.valueOf(movie.getId()) });

		db.close();

		return rows;
	}
}
