package com.myapp.chavaalpha;

import java.util.ArrayList;

import com.myapp.googleapi.GooglePlacesSample;

import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationManager;
import android.location.GpsStatus.Listener;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SecondPageActivity extends Activity {

	public ListView lv;
	public double lat = 37.784147;
	public double lng = -122.402115;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secondpage);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.secondpage, menu);
		
		displayList();
		
		return true;
	}

	public void displayList() {
		lv = (ListView) findViewById(R.id.SecondList);
		ArrayList<String> resultSet = getQueryData();
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				  android.R.layout.simple_list_item_1, android.R.id.text1, resultSet);
		
		lv.setAdapter(adapter); 
		
	}

	private ArrayList<String> getQueryData() {
		Bundle extras = getIntent().getExtras();
		String query = extras.getString("query");
		if(extras.getDouble("lat")!=0.0)
			lat = extras.getDouble("lat") ;
		
		if(extras.getDouble("lng")!=0.0)
			lng = extras.getDouble("lng") ;
		
		//get information from google api
		ArrayList<String> result = new ArrayList<String>();
		result.add("this is first "+query);
		result.add("this is second "+query);
		result.add("this is third "+query);
		
		GooglePlacesSample g= new GooglePlacesSample();
		
		try {
			ArrayList<String> resultSet = g.performSearch(query,lat,lng);
			return resultSet;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	
	}

	

}

