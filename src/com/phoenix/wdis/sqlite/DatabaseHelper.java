package com.phoenix.wdis.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.phoenix.wdis.util.Constants;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String CLASSNAME = "DatabaseHelper";

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "ShowTime";

	// Contacts table name
	protected static final String TABLE_MOVIES = "movies";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {

		Log.d(CLASSNAME, "Creating tables");

		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_MOVIES + "("
				+ Constants.KEY_ID + " INTEGER PRIMARY KEY,"
				+ Constants.KEY_CREATED_DATE
				+ " datetime default current_timestamp,"
				+ Constants.KEY_UPDATED_DATE + " datetime,"
				+ Constants.KEY_NAME + " TEXT," + Constants.KEY_RATING
				+ " TEXT" + ")";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed

		Log.d(CLASSNAME, "Drop older table if existed");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);

		// Create tables again
		onCreate(db);
	}
}