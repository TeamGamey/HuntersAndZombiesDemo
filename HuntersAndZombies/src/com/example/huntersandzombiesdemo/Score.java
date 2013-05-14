package com.example.huntersandzombiesdemo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import android.content.Intent;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

public class Score extends Activity {
//	private TextView scoreView2;
	private ParseUser currentUser;
	private String duelUsername;
	private HashMap<Integer, String> scores;
	private TableLayout scoreView;
	public static String DUEL_USERNAME = "com.example.huntersandzombies.DUEL_USERNAME";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);
		Parse.initialize(this, "LtZV0e5xH56B9pBgRv9PvzsXf2VM8t1sWPkOgsI3", "jDhUAqESu8KPfZLcfOIcb2cq6EaVmNiYE0W0H0XX");
		ParseAnalytics.trackAppOpened(getIntent());
		currentUser = ParseUser.getCurrentUser();
		ParseACL defaultACL = new ParseACL();
		defaultACL.setPublicReadAccess(true);
		
		ParseACL.setDefaultACL(defaultACL, true);
//		scoreView2 = (TextView) findViewById(R.id.scoreView2);
		scoreView = (TableLayout) findViewById(R.id.scoreView);
		ParseQuery queryScores = ParseUser.getQuery();
		queryScores.findInBackground(new FindCallback(){
			public void done(List<ParseObject> object, ParseException e){
				List<Pair<String, Integer>> scoreboard = new ArrayList<Pair<String, Integer>>();
				for (ParseObject obj : object) {
					ParseUser user = (ParseUser)obj;
					String username = user.getUsername();
					int score = user.getInt("score");
					scoreboard.add(new Pair<String, Integer>(username, score));
				}
				for (int i = 0; i < scoreboard.size(); ++i) {
					int j = i;
					while (j - 1 >= 0 && scoreboard.get(j - 1).second < scoreboard.get(j).second) {
						Pair<String, Integer> tmp = scoreboard.get(j - 1);
						scoreboard.set(j - 1, scoreboard.get(j));
						scoreboard.set(j, tmp);
						j--;
					}
				}
				setupScores(scoreboard);
			}
		});		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.score, menu);
		return true;
	}
	private void setupScores(List<Pair<String, Integer>> scoreboard){
		for (Pair<String, Integer> entry : scoreboard){
			TableRow tableRow = new TableRow(this);
			LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			tableRow.setLayoutParams(layoutParams);
			TextView userLabel = new TextView(this);
			userLabel.setText(entry.first + "   ");
			tableRow.addView(userLabel);
			TextView scoreLabel = new TextView(this);
			scoreLabel.setText(entry.second.toString());
			tableRow.addView(scoreLabel);
			scoreView.addView(tableRow, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		}
	}
}
