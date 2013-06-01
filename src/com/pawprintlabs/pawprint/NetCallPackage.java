package com.pawprintlabs.pawprint;

public class NetCallPackage {
	private int statusCode;
	private String authKey;
	private String userID;
	
	public NetCallPackage() {
		statusCode = 500;
		authKey = "none";
		userID = "none";
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getUserID() {
		return userID;
	}

	public String getSessionKey() {
		return authKey;
	}

	public void setSessionKey(String sessionKey) {
		this.authKey = sessionKey;
	}
	
	
}
