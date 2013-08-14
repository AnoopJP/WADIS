package com.phoenix.wdis.sqlite;

import java.util.Date;

import android.database.Cursor;

import com.phoenix.wdis.model.ShowDetails;
import com.phoenix.wdis.util.Constants;

public class RowMapper {

	public static ShowDetails MapRowToShowDetails(Cursor cursor) {

		Date createdDate = null;
		Date updatedDate = null;

		ShowDetails showDetails = new ShowDetails();
		showDetails.setId(cursor.getLong(0));

		if (cursor.getString(cursor.getColumnIndex(Constants.KEY_CREATED_DATE)) != null) {
			createdDate = new Date(Long.parseLong(cursor.getString(cursor
					.getColumnIndex(Constants.KEY_CREATED_DATE))));
			showDetails.setCreatedDate(createdDate);
		}

		if (cursor.getString(cursor.getColumnIndex(Constants.KEY_UPDATED_DATE)) != null) {
			updatedDate = new Date(Long.parseLong(cursor.getString(cursor
					.getColumnIndex(Constants.KEY_UPDATED_DATE))));
			showDetails.setUpdatedDate(updatedDate);
		}

		showDetails.setRating(cursor.getString(cursor
				.getColumnIndex(Constants.KEY_RATING)));
		showDetails.setShowName(cursor.getString(cursor
				.getColumnIndex(Constants.KEY_NAME)));

		return showDetails;
	}
}
