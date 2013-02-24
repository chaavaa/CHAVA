package com.myapp.googleapi;



import java.util.ArrayList;
import java.util.Iterator;



import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.api.client.http.json.JsonHttpParser;
import com.google.api.client.json.jackson.JacksonFactory;
import com.myapp.googleapi.Place.Result;


public class GooglePlacesSample {

	//private static final GooglePlacesSample INSTANCE = new GooglePlacesSample();
	// Create our transport.
	private static final HttpTransport transport = new ApacheHttpTransport();
	
	// Fill in the API key you want to use.
	private static final String API_KEY = "AIzaSyDNZWkRM4lxn8gtAMwe2zZZWjnRrgAON1U";
	
	// The different Places API endpoints.
	private static final String PLACES_TEXT_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/textsearch/json";
	
	private static final boolean PRINT_AS_STRING = false;
	
	// Moscone Center, Howard Street, San Francisco, CA, United States
	double latitude = 37.784147;
	double longitude = -122.402115;
	
	// telenet
	//double latitude = 51.034823;
	//double longitude = 4.483774;

	// home
	//double latitude = 50.963062;
	//double longitude = 3.522255;
	
	
	
	public ArrayList<String> performSearch(String query,double latitude , double longitude) throws Exception {
		try {
			ArrayList<String> resultSet = new ArrayList<String>();
			
			System.out.println("Perform Search ....");
			System.out.println("-------------------");
			HttpRequestFactory httpRequestFactory = createRequestFactory(transport);
			HttpRequest request = httpRequestFactory.buildGetRequest(new GenericUrl(PLACES_TEXT_SEARCH_URL));
			request.url.put("key", API_KEY);
			request.url.put("location", latitude + "," + longitude);
			request.url.put("radius", 5000); //metres
			request.url.put("sensor", "false");
			request.url.put("query", query);
			
			if (PRINT_AS_STRING) {
				System.out.println(request.execute().parseAsString());
			} else {
				Place c = request.execute().parseAs(Place.class);
				System.out.println(c);
				ArrayList<Result> r = c.results;
				Iterator it = r.iterator();
				while(it.hasNext()){
					Result r1= (Result) it.next();
					resultSet.add(r1.toString());
					System.out.println(r1);
				}
				
				
			}
			
			return resultSet;
		} catch (HttpResponseException e) {
			System.err.println(e.response.parseAsString());
			throw e;
		}
	}
	
	
	
	
	
	
	public static HttpRequestFactory createRequestFactory(final HttpTransport transport) {
			   
		  return transport.createRequestFactory(new HttpRequestInitializer() {
		   public void initialize(HttpRequest request) {
		    JsonHttpParser parser = new JsonHttpParser();
		    parser.jsonFactory = new JacksonFactory();
		    request.addParser(parser);
		   }
		});
	}
	
	
}
