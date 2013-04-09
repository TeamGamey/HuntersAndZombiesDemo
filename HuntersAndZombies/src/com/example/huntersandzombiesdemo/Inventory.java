package com.example.huntersandzombiesdemo;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Inventory extends Activity {
	private TextView moneyLeft;
	private Button returnToDash;
	private int money;
	private ArrayList<String> inventory;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inventory);
		moneyLeft = (TextView) findViewById(R.id.moneyLeft);
		Bundle extras = getIntent().getExtras();
		
		if(extras!=null){
			money = extras.getInt(Dashboard.USER_MONEY);
			inventory = extras.getStringArrayList(Dashboard.INVENTORY);
			
//			 if(money!=null){
				moneyLeft.setText(money);
//			}
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inventory, menu);
		return true;
	}
	public void purchaseItem1(View view){
		//Add item to inventory
		//Subtract Dashboard.money flow
		if( Dashboard.money >= 100){
			Dashboard.money -= 100;
			Dashboard.inventory.add("Baseball Bat");
			moneyLeft.setText(Dashboard.money);
			//Remove button
			Button button = (Button) findViewById(R.id.button1);        
		    button.setOnClickListener(new OnClickListener() {
		        public void onClick(View view) {
		            ViewGroup parentView = (ViewGroup) view.getParent();
		            parentView.removeView(view);
		        }
		    });
		}		
	}
	public void purchaseItem2(View view){
		//Add item to inventory
		//Subtract Dashboard.money flow
		if( Dashboard.money >= 200){
			Dashboard.money -= 200;
			Dashboard.inventory.add("Golf Club");
			moneyLeft.setText(Dashboard.money);
		//Remove button
		Button button = (Button) findViewById(R.id.button2);        
	    button.setOnClickListener(new OnClickListener() {
	        public void onClick(View view) {
	            ViewGroup parentView = (ViewGroup) view.getParent();
	            parentView.removeView(view);
	        }
	    });
		}
	}
	public void purchaseItem3(View view){
		//Add item to inventory
		//Subtract Dashboard.money flow
		if( Dashboard.money >= 300){
			Dashboard.money -= 300;
			Dashboard.inventory.add("Mace");
			moneyLeft.setText(Dashboard.money);
			//Remove button
		Button button = (Button) findViewById(R.id.button3);        
	    button.setOnClickListener(new OnClickListener() {
	        public void onClick(View view) {
	            ViewGroup parentView = (ViewGroup) view.getParent();
	            parentView.removeView(view);
	        }
	    });
		}
	}
	public void purchaseItem4(View view){
		//Add item to inventory
		//Subtract Dashboard.money flow
		if( Dashboard.money >= 400){
			Dashboard.money -= 400;
			Dashboard.inventory.add("Flail");
			moneyLeft.setText(Dashboard.money);
			//Remove button
		Button button = (Button) findViewById(R.id.button4);        
	    button.setOnClickListener(new OnClickListener() {
	        public void onClick(View view) {
	            ViewGroup parentView = (ViewGroup) view.getParent();
	            parentView.removeView(view);
	        }
	    });
		}
	}
	public void purchaseItem5(View view){
		//Add item to inventory
		//Subtract Dashboard.money flow
		if( Dashboard.money >= 500){
			Dashboard.money -= 500;
			Dashboard.inventory.add("Crowbar");
			moneyLeft.setText(Dashboard.money);
		//Remove button
		Button button = (Button) findViewById(R.id.button5);        
	    button.setOnClickListener(new OnClickListener() {
	        public void onClick(View view) {
	            ViewGroup parentView = (ViewGroup) view.getParent();
	            parentView.removeView(view);
	        }
	    });
		}
	}	
	

}
