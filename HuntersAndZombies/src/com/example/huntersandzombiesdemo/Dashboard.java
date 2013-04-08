package com.example.huntersandzombiesdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
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
		
//		scoreButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dashboard, menu);
		return true;
	}

}
