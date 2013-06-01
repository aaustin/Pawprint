package com.pawprintlabs.pawprint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class RemoteInterface {

	private HttpClient getGenericHttpClient() {
		int timeout = 10000;
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, timeout);
		HttpConnectionParams.setSoTimeout(httpParams, timeout);
		return new DefaultHttpClient(httpParams);
	}

	private NetCallPackage processEntityForNCP (HttpEntity entity, int statusCode) {
		try {
			if (entity != null) {
		    	InputStream instream = entity.getContent();
		  
		    	BufferedReader rd = new BufferedReader(new InputStreamReader(instream));
		    	// convert the entity sent from the server to a JSON object
		    	String line = rd.readLine();
		    	NetCallPackage ncp = new NetCallPackage();
		    	ncp.setStatusCode(statusCode);
		    	JSONObject jsonreturn;
		    	if (line != null) {
		    		Log.i(getClass().getSimpleName(), line);
		    		jsonreturn = new JSONObject(line);
			    	return jsonreturn;
			    } 			    				
		    }
		} catch (JSONException ex) {
	   		Log.i(getClass().getSimpleName(), "JSON exception: " + ex.getMessage());
	   	} catch (IOException ex) { 
    		Log.i(getClass().getSimpleName(), "IO exception: " + ex.getMessage());
		}
		return null;
	}
	
	private NetCallPackage make_restful_get(String url) {
		try {    	
			Log.i(getClass().getSimpleName(), url);
		    HttpGet request = new HttpGet(url);
		    request.setHeader("Content-type", "applications/json");
		    HttpResponse response = getGenericHttpClient().execute(request);
		    
		    return processEntityForNCP(response.getEntity(), response.getStatusLine().getStatusCode());
		} catch (ClientProtocolException ex) {
	    		Log.i(getClass().getSimpleName(), "Client protocol exception: " + ex.getMessage());
		} catch (IOException ex) { 
    		Log.i(getClass().getSimpleName(), "IO exception: " + ex.getMessage());
		}
		return null;
	}

	private NetCallPackage make_restful_post(JSONObject body, String url) {
		try {    	
			Log.i(getClass().getSimpleName(), url);
		    HttpPost request = new HttpPost(url);
		    request.setEntity(new ByteArrayEntity(body.toString().getBytes("UTF8")));
		    request.setHeader("Content-type", "applications/json");
		    HttpResponse response = getGenericHttpClient().execute(request);

		    return processEntityForNCP(response.getEntity(), response.getStatusLine().getStatusCode());
		} catch (ClientProtocolException ex) {
	    		Log.i(getClass().getSimpleName(), "Client protocol exception: " + ex.getMessage());
		} catch (IOException ex) { 
    		Log.i(getClass().getSimpleName(), "IO exception: " + ex.getMessage());
		}
		return null;
	}
}
