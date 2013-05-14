package com.example.huntersandzombiesdemo;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class AcceptDuel extends Activity {
	private ParseUser currentUser;
	private Button duelBtn;
	private TextView description;
	private String duelUser;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accept_duel);
		Parse.initialize(this, "LtZV0e5xH56B9pBgRv9PvzsXf2VM8t1sWPkOgsI3", "jDhUAqESu8KPfZLcfOIcb2cq6EaVmNiYE0W0H0XX");
		currentUser = ParseUser.getCurrentUser();
		try {
		String action = getIntent().getAction();
		String channel = getIntent().getExtras().getString("com.parse.Channel");
		JSONObject json = new JSONObject(getIntent().getExtras().getString("com.parse.Data"));
		Log.d("acceptDuel", "got action " + action + " on channel " + channel);
		duelUser = json.getString("user");
		//String weapon = json.getString("weapon");
		description = (TextView) findViewById(R.id.userChallenge);
		description.setText(duelUser+" has challenged you to a duel! Select a weapon to fight back!");
		duelBtn = (Button) findViewById(R.id.respondToDuel);
		duelBtn.setOnClickListener(new OnClickListener(){
			@Override
		      public void onClick(View v) {
				boolean wonDuel = (Math.random() < 0.5);
				String message = "";
				String title = "";
				String callbackResponse = "";
				JSONObject data;
				if(!wonDuel){
					title = "Whoops!";
					message = "Sorry, "+duelUser+" has won this match! Your score has decreased by something.";
					callbackResponse = "You have won a duel against "+currentUser.getUsername()+"!"; //response to send back to sending person;
				}
				else{
					title = "Congratulations!";
					message = "You have won a duel against "+duelUser+"!";
					callbackResponse = "Sorry, you have lost a duel against "+currentUser.getUsername()+"."; //response to send back to sending person;
				}
				//send response to other user;
				//TODO: add repsonse handling for these things
				ParseQuery pushQuery = ParseInstallation.getQuery();
				pushQuery.whereEqualTo("username", duelUser);
				ParsePush push = new ParsePush();
				push.setQuery(pushQuery);
				push.setMessage(callbackResponse);
				push.sendInBackground();
				//show result to user
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						AcceptDuel.this);
				alertDialogBuilder.setTitle(title);
				alertDialogBuilder.setMessage(message);
				alertDialogBuilder.setNeutralButton("Close",new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						launchIntent(); //return to dashboard
					}
				} ).show(); 
			}
		});
	
		}catch(JSONException e) {
		      Log.d("acceptDuel", "JSONException: " + e.getMessage());
	    }
		
		
	}
	private void launchIntent() {
        Intent it = new Intent(AcceptDuel.this,Dashboard.class);
        startActivity(it); 
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.accept_duel, menu);
		return true;
	}

}
