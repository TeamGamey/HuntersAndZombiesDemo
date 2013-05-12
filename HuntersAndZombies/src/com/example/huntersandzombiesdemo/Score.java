package com.example.huntersandzombiesdemo;

import java.util.Date;
import java.util.List;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Score extends Activity {
	private TextView scoreView;
	private Button testParseBtn;
	private ParseUser currentUser;
	private ParseObject location;
	double longitude;
	double latitude;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);
//		Parse.initialize(this, "IVMpQf3ccNsiWfdfufivTkjlMHOYC5dgAO8APfjB", "aeC7EJihUm9MQw5lZqw38OnIWvhAY93MJ2JLDm3M");
		Parse.initialize(this, "LtZV0e5xH56B9pBgRv9PvzsXf2VM8t1sWPkOgsI3", "jDhUAqESu8KPfZLcfOIcb2cq6EaVmNiYE0W0H0XX");
		ParseAnalytics.trackAppOpened(getIntent());
		currentUser = ParseUser.getCurrentUser();
		ParseACL defaultACL = new ParseACL();
		defaultACL.setPublicReadAccess(true);
		
		ParseACL.setDefaultACL(defaultACL, true);
		scoreView = (TextView) findViewById(R.id.scoreView);
		scoreView.setText("???");
//		EditText text = (EditText) findViewById(R.id.editText);
//		text.setText("ASDFGHJ");
		testParseBtn = (Button) findViewById(R.id.testButton);
//		location2 = new ParseObject("Locations2");
//		location = null;
		
/*		testParseBtn.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View arg0){
				latitude = Math.random();
				longitude = Math.random();
				ParseQuery query = new ParseQuery("Locations");
				query.whereEqualTo("username", currentUser);
//				testParseBtn.setText("lat = " + latitude + ", long = " + longitude);
				query.findInBackground(new FindCallback(){
					public void done(List<ParseObject> object, ParseException e){
//						testParseBtn.setText("Retrieved " + object.size() + " locations.");
//						Log.i("parse", "Retrieved " + object.size() + " locations");
//						Log.i("parse", "Retrieved" + object.get(0).toString());
//						Log.i("parse", "" + object.get(0));
						if (object != null && object.size() > 0) {
							Date mostRecent = null;
							ParseObject mostRecentObj = null;
							for (ParseObject obj : object) {
								Date updatedAt = obj.getDate("updatedAt");
								if (mostRecent == null || mostRecent.compareTo(updatedAt) <= 0) {
									mostRecent = updatedAt;
									mostRecentObj = obj;
								}
							}
							location = mostRecentObj;
						} else {
//							testParseBtn.setText("No locations.");
							location = new ParseObject("Locations");
						}
						location.put("longitude", longitude);
						location.put("latitude", latitude);	
						location.put("username",currentUser);
						location.saveInBackground(new SaveCallback(){
							public void done(ParseException e){
								location.put("latitude", latitude+1);
								location.put("longitude", longitude+1);
								location.saveInBackground();					
							}
						});
					}
				});
			}
		});
*/	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.score, menu);
		return true;
	}

}
