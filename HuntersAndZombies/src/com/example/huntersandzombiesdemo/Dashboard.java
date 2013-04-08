package com.example.huntersandzombiesdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Dashboard extends Activity {
	private Button inventoryButton;
	private Button scoreButton;
	private Button duelButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		
		scoreButton = (Button) findViewById(R.id.scoreButton);
		duelButton = (Button) findViewById(R.id.duelButton);
		inventoryButton = (Button) findViewById(R.id.inventoryButton);
		//add event handlers to change view
		
		scoreButton.setOnClickListener(scoreHandler);
		inventoryButton.setOnClickListener(inventoryHandler);
		duelButton.setOnClickListener(duelHandler);
//		scoreButton.setOnClickListener(this);
	}
	/*
	 * on click listener for the scorebutton
	 */
	View.OnClickListener scoreHandler = new View.OnClickListener() {		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(Dashboard.this, Score.class);
			startActivity(intent);
		}
	};
	
	/**
	 * On click listener for the inventory button
	 */
	View.OnClickListener inventoryHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
//			Intent intent = new Intent(this, ...intent..);
//			startActivity(intent);
		}
	};
	
	/**
	 * On click listener for the duel handler button
	 */
	View.OnClickListener duelHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(Dashboard.this, Duel.class);
			startActivity(intent);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dashboard, menu);
		return true;
	}

}
