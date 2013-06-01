package com.pawprintlabs.pawprint;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class SplashActivity extends Activity {
	private static long DISPLAY_TIMER = 2000;
	private Timer faketimer;
	PrefHelper pHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		pHelper = new PrefHelper(getApplicationContext());
		
		pause_for_display();
	}

	private void pause_for_display() {
		faketimer = new Timer();
		faketimer.schedule(new TimerTask() {
			public void run() {
				load_next_activity();
			}			
		}, DISPLAY_TIMER);
	}
	
	private void load_next_activity() {
		if (pHelper.get_login_status()) {
			Intent i = new Intent(getApplicationContext(), LoginRegisterActivity.class);
			startActivity(i);
			finish();
		} else {
			Intent i = new Intent(getApplicationContext(), MainActivity.class);
			startActivity(i);
			finish();
		}
	}
}
