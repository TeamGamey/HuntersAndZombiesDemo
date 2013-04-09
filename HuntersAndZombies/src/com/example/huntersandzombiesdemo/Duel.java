package com.example.huntersandzombiesdemo;

import java.util.ArrayList;
import java.util.HashMap;

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
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_duel);
		mTlayout = (TableLayout) findViewById(R.id.tableView);
		users = new HashMap<Integer, String>();
		users.put(1234,"Zombie123");
		users.put(5343, "Zombie Monster");
		users.put(3599, "Monster1234524");
		setupUsers();
		
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
