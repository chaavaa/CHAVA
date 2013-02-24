package com.myapp.googleapi;

import java.util.ArrayList;

import com.google.api.client.util.Key;

public class Place {
	
	@Key
	public String status;

	@Key
	public ArrayList<Result> results;

	@Override
	public String toString() {
		return status + " "+ results.size();
	}
	
	public static class Result {

		@Key
		public String formatted_address;
		
		@Key
		public String name;
		
		@Key
		public String id;
		@Key
		public Geometry geometry;
		
		@Override
		public String toString(){
			//return name+"  "+formatted_address + " *** " + id + " *** " + geometry.location.lng;
			return name+"  "+formatted_address;
		}
		
		public static class Geometry {

			@Key
			public Location location;
			
			public static class Location {

				@Key
				public float lat;
				
				@Key
				public float lng;
			}
		}
	}

}





