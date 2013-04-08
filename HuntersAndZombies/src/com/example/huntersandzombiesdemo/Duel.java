package com.example.huntersandzombiesdemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Duel extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_duel);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.duel, menu);
		return true;
	}

}
