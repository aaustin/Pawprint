package com.pawprintlabs.pawprint;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class PrefHelper {
	private static final String APP_SHARED_PREFS = "com.pawprint.mainpref";

	private static final String LOGIN_STATUS = "first_login_status";
	private static final String USER_EMAIL = "email_address";
	private static final String SESSION_KEY = "server_sesssion_key";
	private static final String USER_ID = "user_id";
	
	private SharedPreferences appSharedPrefs;
	private Editor prefsEditor;	
	
	Context ctx;
	
	public PrefHelper(Context context) {
		this.appSharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Context.MODE_PRIVATE);
		
		ctx = context;
		this.prefsEditor = appSharedPrefs.edit();
	}
		
	// check if internet is enabled
 	public boolean check_enable_online() {
 	    ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
 	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
 	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
 	        return true;
 	    }
 	    return false;
 	}
 	
 	// has user logged in yet
 	public boolean get_login_status() {
 		return appSharedPrefs.getBoolean(LOGIN_STATUS, false);
 	}
 		
 	public void set_logged_in() {
 		prefsEditor.putBoolean(LOGIN_STATUS, true);
 		prefsEditor.commit();
 	}

	// set and get user email
	public String get_user_email() {
		return appSharedPrefs.getString(USER_EMAIL, "none");
	}
		
	public void set_user_email(String email) {
		prefsEditor.putString(USER_EMAIL, email);
		prefsEditor.commit();
	}
	
	// set and get web session key
	public String get_session_key() {
		return appSharedPrefs.getString(SESSION_KEY, "none");
	}
		
	public void set_session_key(String key) {
		prefsEditor.putString(USER_EMAIL, key);
		prefsEditor.commit();
	}		
	
	// set and get user id
	public int get_user_id() {
		return appSharedPrefs.getInt(USER_ID, -1);
	}
		
	public void set_user_id(int id) {
		prefsEditor.putInt(USER_ID, id);
		prefsEditor.commit();
	}	
}
