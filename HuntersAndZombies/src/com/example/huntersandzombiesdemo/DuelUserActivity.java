package com.example.huntersandzombiesdemo;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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


public class DuelUserActivity extends Activity {
	private String duelUsername;
	private TextView userLabel;
	private Button duelBtn;
	private ArrayList<String> inventory;
	private int money;
	private ParseUser currentUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_duel_user);
		Parse.initialize(this, "LtZV0e5xH56B9pBgRv9PvzsXf2VM8t1sWPkOgsI3", "jDhUAqESu8KPfZLcfOIcb2cq6EaVmNiYE0W0H0XX");
		currentUser = ParseUser.getCurrentUser();
		Bundle bundle = getIntent().getExtras();
		if (bundle!=null){
			duelUsername = bundle.getString(Duel.DUEL_USERNAME);
			userLabel = (TextView) findViewById(R.id.duelUserName);
			userLabel.setText(duelUsername);
			inventory = bundle.getStringArrayList(Dashboard.INVENTORY);
			money = bundle.getInt(Dashboard.USER_MONEY);	
		}
		
		duelBtn = (Button) findViewById(R.id.duelButton);
		duelBtn.setOnClickListener(new OnClickListener(){
			@Override
		      public void onClick(View v) {
//				Intent intent = new Intent(Duel.this, DuelUserActivity.class);
//				intent.putExtra(DUEL_USERNAME, users.get(v.getId()));
//				startActivity(intent); 
//				Thread.sleep(1000);
				
				JSONObject data;
				try {
					data = new JSONObject(//"{\"action\": \"com.example.huntersandzombiesdemo.DUEL_USER\"," +
							"{\"alert\": \""+currentUser.getUsername()+" has challenged you to a duel!\","+
							"\"user\": \""+currentUser.getUsername()+"\","+
							"\"weapon\": \"wooden sword\"}");
					ParseQuery pushQuery = ParseInstallation.getQuery();
					pushQuery.whereEqualTo("username", duelUsername);
					ParsePush push = new ParsePush();
					push.setQuery(pushQuery);
					push.setData(data);
					push.sendInBackground();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

//				push.setMessage(currentUser.getUsername()+" has challenged you to a duel!");
				
//				
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						DuelUserActivity.this);
				alertDialogBuilder.setTitle("Duel Sent to "+duelUsername);
				alertDialogBuilder.setMessage("You have initiated a duel against "+duelUsername+"!\nNow it is their turn to fight back");
				//money+=20;
				alertDialogBuilder.setNeutralButton("Close",new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						launchIntent();
					}
				} ).show(); 
		      }
		});
	}
	
	private void launchIntent() {
        Intent it = new Intent(DuelUserActivity.this,Dashboard.class);
        it.putExtra(Dashboard.USER_MONEY,money );
        startActivity(it); 
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.duel_user, menu);
		return true;
	}

}
