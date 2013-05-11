package com.example.huntersandzombiesdemo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class Duel extends Activity {
	private HashMap<Integer, String> users;
	private TableLayout mTlayout;
	public static String DUEL_USERNAME = "com.example.huntersandzombies.DUEL_USERNAME";
	private String username;
	private ArrayList<String> inventory;
	private int money;
	//map user to distance away (in meters or something
	private ParseUser currentUser;
	private int i;
	private ParseGeoPoint myParseGP;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_duel);
		users = new HashMap<Integer, String>();
		mTlayout = (TableLayout) findViewById(R.id.tableView);

		Parse.initialize(this, "LtZV0e5xH56B9pBgRv9PvzsXf2VM8t1sWPkOgsI3", "jDhUAqESu8KPfZLcfOIcb2cq6EaVmNiYE0W0H0XX");
		ParseAnalytics.trackAppOpened(getIntent());
		currentUser = ParseUser.getCurrentUser();
//		users.put(1234,"Zombie123");
		ParseQuery query = new ParseQuery("Locations");
		query.whereEqualTo("username", currentUser);
		query.findInBackground(new FindCallback(){
			public void done(List<ParseObject> object, ParseException e){
				
				if (object != null && object.size() > 0) {
					for (ParseObject obj : object) {
						myParseGP = obj.getParseGeoPoint("location");
					}
				}
			} 
			//									location.put("longitude", myLocation.getLongitude());
			//									location.put("latitude", myLocation.getLatitude());	
		});		

		//Retrieving other locations
		ParseQuery queryOtherLoc = new ParseQuery("Locations");
		queryOtherLoc.whereWithinKilometers("location", myParseGP, .01);
		queryOtherLoc.whereNotEqualTo("username", currentUser);
		queryOtherLoc.findInBackground(new FindCallback(){
			public void done(List<ParseObject> object, ParseException e){
				users.clear();
				i = 0;
				if (object != null && object.size() > 0){
					for (ParseObject obj : object){
						users.put(i, obj.get("username").toString());
						i++;
					}
				}
				setupUsers();
			}
		});							


		//		users.put(5343, "Zombie Monster");
		//		users.put(3599, "Monster1234524");

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			username = extras.getString(Dashboard.USER_NAME);
			inventory = extras.getStringArrayList(Dashboard.INVENTORY);
			money = extras.getInt(Dashboard.USER_MONEY);
		}
	}

	private void setupUsers(){

		for (int id : users.keySet()){
			TableRow tr=new TableRow(this);
			LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			tr.setLayoutParams(lp);
			TextView userLabel = new TextView(this);
			userLabel.setText(users.get(id));
			tr.addView(userLabel);
			Button btn = new Button(this);
			btn.setText("duel");
			btn.setId(id);
			btn.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					Bundle bundle = new Bundle();
					bundle.putString(DUEL_USERNAME, users.get(v.getId()));
					bundle.putInt(Dashboard.USER_MONEY, money);
					bundle.putStringArrayList(Dashboard.INVENTORY, inventory);
					Intent intent = new Intent(Duel.this, DuelUserActivity.class);
					intent.putExtras(bundle);
					startActivity(intent); 
				}
			});
			tr.addView(btn);
			mTlayout.addView(tr, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.duel, menu);
		return true;
	}



}
