package com.phoenix.wdis.app;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import com.phoenix.wdis.sqlite.DatabaseHelper;
import com.phoenix.wdis.util.Constants;

/**
 * Get application instance
 * 
 * @author sharika
 */
public class WDISApp extends Application {

	private static WDISApp sharedApplication;

	private static DatabaseHelper dbHelper;
	private static SQLiteDatabase db;

	private Activity currentActivity;

	@Override
	public void onCreate() {
		super.onCreate();
		sharedApplication = this;
		// DatabaseHelper db = new DatabaseHelper(this);
	}

	public void saveGPSState(boolean isChecked) {
		SharedPreferences.Editor prefsEditor = getAppPreferences().edit();
		prefsEditor.putBoolean(Constants.kGPSLocation, isChecked);
		prefsEditor.commit();
	}

	public void saveUserName(String userName) {
		SharedPreferences.Editor prefsEditor = getAppPreferences().edit();
		prefsEditor.putString(Constants.kUserName, userName);
		prefsEditor.commit();
	}

	public boolean getGPSState() {
		return getAppPreferences().getBoolean(Constants.kGPSLocation, true);
	}

	public String getUserName() {
		return getAppPreferences().getString(Constants.kUserName, "");
	}

	public static SQLiteDatabase getDb() {
		return db;
	}

	public static void setDb(SQLiteDatabase db) {
		WDISApp.db = db;
	}

	public static synchronized WDISApp getSharedApplication() {
		return sharedApplication;
	}

	/**
	 * @return SharedPreferences object
	 */

	public static SharedPreferences getAppPreferences() {

		SharedPreferences preferences = WDISApp.getSharedApplication()
				.getSharedPreferences(Constants.kSharedPreference,
						MODE_WORLD_READABLE);
		return preferences;
	}

	public void setUserTypeId(String userTypeId) {
		SharedPreferences.Editor prefsEditor = getAppPreferences().edit();
		prefsEditor.putString(Constants.kUserTypeId, userTypeId);
		prefsEditor.commit();
	}

	public String getUserTypeId() {
		String careGiverRole = getAppPreferences().getString(
				Constants.kUserTypeId, null);
		return careGiverRole;
	}

	public static float getCurrentDensity() {
		return WDISApp.getSharedApplication().getResources()
				.getDisplayMetrics().density;
	}

	public void createDatabase() {
		dbHelper = new DatabaseHelper(getApplicationContext());
		db = dbHelper.getWritableDatabase();
	}

	/**
	 * @return the currentActivity
	 */
	public Activity getCurrentActivity() {
		return currentActivity;
	}

	/**
	 * @param currentActivity
	 *            the currentActivity to set
	 */
	public void setCurrentActivity(Activity currentActivity) {
		this.currentActivity = currentActivity;
	}

}
