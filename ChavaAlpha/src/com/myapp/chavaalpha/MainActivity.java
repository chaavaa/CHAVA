package com.myapp.chavaalpha;

import java.util.ArrayList;

import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	public ListView lv;
	public LocationManager locationManager = null;
	public Location location = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainpage);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mainpage, menu);
		displayList();
		return true;
	}

	
	public void displayList(){
		getLocation();
		lv = (ListView) findViewById(R.id.MainList);
	//	 String[] values = new String[] { "Walmart", "Starbucks"};
		final ArrayList<String> values = new ArrayList<String>();
		values.add("walmart");
		values.add("starbucks");
		values.add("subway");
		 
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				  android.R.layout.simple_list_item_1, android.R.id.text1, values);
		
		lv.setAdapter(adapter); 	
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> parent, View view,
			    int position, long id) {
			    Toast.makeText(getApplicationContext(),
			      "Click ListItem Number " + position, Toast.LENGTH_LONG)
			      .show();
			    
			    Intent i = new Intent(getApplicationContext(), SecondPageActivity.class);
			    i.putExtra("query",values.get(position));
			    if(location != null){
			    	i.putExtra("lat", location.getLatitude());
			    	i.putExtra("lng", location.getLongitude());
			    }else{
			    	i.putExtra("lat", 0.0);
			    	i.putExtra("lng", 0.0);
			    }
			    startActivity(i);
			    
			  }

			});
	}
	
	public void getLocation(){
		locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
		location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if(location == null)
		{
			Toast.makeText(getApplicationContext(),"Start the GPS ", Toast.LENGTH_LONG).show();
			Log.d("GPSStatus", "Please Start the GPS");
			
		}
		
		locationManager.addGpsStatusListener(new Listener() {
			
			@Override
			public void onGpsStatusChanged(int event) {
				switch (event) {
			    case GpsStatus.GPS_EVENT_FIRST_FIX:
			        Log.d("GPSStatus", "GPS First Fix");
			        Toast.makeText(getApplicationContext(),"GPS First Fix ", Toast.LENGTH_LONG).show();
			        break;
			    case GpsStatus.GPS_EVENT_STARTED:
			        Log.d("GPSStatus", "GPS Started");
			        Toast.makeText(getApplicationContext(),"GPS Started ", Toast.LENGTH_LONG).show();
			        break;
			    case GpsStatus.GPS_EVENT_STOPPED:
			        Log.d("GPSStatus", "GPS Stopped");
			        Toast.makeText(getApplicationContext(),"GPS Stopped ", Toast.LENGTH_LONG).show();
			        break;
			    }
			}
		});
	}
	
}
