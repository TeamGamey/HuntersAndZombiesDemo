package com.example.huntersandzombiesdemo;

import java.util.List;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class AcceptDuel extends Activity {
	private ParseUser currentUser;
	private Button duelBtn;
	private TextView description;
	private String duelUser;
	private List<Boolean> inventory;
	

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
		final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup2);
		final RadioButton[] rb = new RadioButton[5];
		for (int i = 0; i<5; i++){
			rb[i] = new RadioButton(this);
		}
		ParseQuery query = new ParseQuery("Money");
		query.whereEqualTo("username", currentUser);
		query.findInBackground(new FindCallback(){
			public void done(List<ParseObject> object, ParseException e){
				for (ParseObject obj: object){
					inventory = obj.getList("inventory");
				}

				if (inventory.get(0)){
					rb[0].setText("Baseball Bat");
					rg.addView(rb[0]);				
				}
				if (inventory.get(1)){
					rg.addView(rb[1]);
					rb[1].setText("Golf Club");
				}
				if (inventory.get(2)){
					rg.addView(rb[2]);
					rb[2].setText("Mace");
				}
				if (inventory.get(3)){
					rg.addView(rb[3]);
					rb[3].setText("Flail");
				}
				if (inventory.get(4)){
					rg.addView(rb[4]);
					rb[4].setText("Crowbar");
				}

			}
		});

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
					int newscore = (Integer) currentUser.get("score")-1;
					currentUser.put("score",newscore);
					currentUser.saveInBackground();

				}
				else{
					title = "Congratulations!";
					message = "You have won a duel against "+duelUser+"!";
					callbackResponse = "Sorry, you have lost a duel against "+currentUser.getUsername()+"."; //response to send back to sending person;
					int newscore = (Integer) currentUser.get("score")+1;
					currentUser.put("score",newscore);
					currentUser.saveInBackground();
					ParseQuery query = new ParseQuery("Money");
					query.whereEqualTo("username", currentUser);
					query.findInBackground(new FindCallback(){
						public void done(List<ParseObject> object, ParseException e){
							ParseObject money = object.get(0);
							money.increment("money", 20);
							money.saveInBackground();
						}
					});
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
