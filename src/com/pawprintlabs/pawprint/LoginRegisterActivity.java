package com.pawprintlabs.pawprint;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;

public class LoginRegisterActivity extends Activity {

	private boolean register;
	
	PrefHelper pHelper;
	
	EditText etxtEmail;
	EditText etxtPassword;
	EditText etxtPasswordConf;
	TextView txtPasswordConf;
	TextView txtLoginRegSwitch;
	Button cmdLoginRegister;
	
	static NetworkProgressBar progBar;
	ServerCallDone serverCallDone;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_register);
		
		register = true;
		
		pHelper = new PrefHelper(getApplicationContext());
		progBar = new NetworkProgressBar(getApplicationContext());
		serverCallDone = new ServerCallDone();
					
		etxtEmail = (EditText) findViewById(R.id.etxtEmail);
		etxtPassword = (EditText) findViewById(R.id.etxtPassword1);
		etxtPasswordConf = (EditText) findViewById(R.id.etxtPassword2);
		txtPasswordConf = (TextView) findViewById(R.id.txtPasswordConf);
		txtLoginRegSwitch = (TextView) findViewById(R.id.txtSwitch);
		cmdLoginRegister = (Button) findViewById(R.id.cmdLoginRegister);
		
		txtLoginRegSwitch.setOnClickListener(new OnClickListener () {
			@Override
			public void onClick(View arg0) {
				switch_interface();
			}
		});
		
		cmdLoginRegister.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				init_network_call();
			}
		});
	}

	private void init_network_call() {
		InputMethodManager imm = (InputMethodManager) getSystemService(
			    INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
		
				
		Runnable runnable;
		if (register) {
			progBar.show("registering account..");
			runnable = new Runnable() {
				public void run() {
					// call register 
				}
			};
		} else {
			progBar.show("logging in..");
			runnable = new Runnable() {
				public void run() {
					// call login 
				}
			};
		}		
		new Thread(runnable).start();
	}
	
	private void switch_interface() {
		if (register) {
			register = false;
			cmdLoginRegister.setText("Login");
			txtPasswordConf.setVisibility(TextView.INVISIBLE);
			etxtPasswordConf.setVisibility(EditText.INVISIBLE);
			txtLoginRegSwitch.setText("Need to register? Click here to register");
			
		} else {
			register = true;
			cmdLoginRegister.setText("Register");
			txtPasswordConf.setVisibility(TextView.VISIBLE);
			etxtPasswordConf.setVisibility(EditText.VISIBLE);
			txtLoginRegSwitch.setText("Already registered? Click here to login");
		}
	}
		
	public class ServerCallDone implements NetworkCallback {
		@Override
		public void finished(NetCallPackage serverResponse) {
			if (serverResponse.getStatusCode() == 200) {
				progBar.change_message("success!");							
	    		progBar.hide();
				pHelper.set_logged_in();
				
			} else if (serverResponse.getStatusCode() >= 0 ) {
				progBar.change_message("error!");							
				progBar.hide();
				handle_server_error(serverResponse);
			} else {
				progBar.change_message("connection failed! try again!!");							
				progBar.hide();
			}
			
		}		
	}
	
	private void handle_server_error(NetCallPackage serverResponse) {
		if (register) {
			
		} else {
			
		}
	}
}
