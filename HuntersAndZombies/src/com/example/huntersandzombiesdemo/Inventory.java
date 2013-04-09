package com.example.huntersandzombiesdemo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Inventory extends Activity {
	private TextView moneyLeft;
	private Button returnToDashBtn;
	private int money;
	private ArrayList<String> inventory;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inventory);

		
		moneyLeft = (TextView) findViewById(R.id.moneyLeft);
		returnToDashBtn = (Button)findViewById(R.id.inventoryToDashbtn);
		returnToDashBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 Bundle bundle = new Bundle();
				 bundle.putInt(Dashboard.USER_MONEY, money);
				 bundle.putStringArrayList(Dashboard.INVENTORY, inventory);
				 Intent intent = new Intent(Inventory.this, Dashboard.class);
				 intent.putExtras(bundle);
				 setResult(RESULT_OK, intent);
				 startActivity(intent);
				 finish();
			}
			
		});
		
		Bundle extras = getIntent().getExtras();
		if(extras!=null){
			
			money = extras.getInt(Dashboard.USER_MONEY);
			inventory = extras.getStringArrayList(Dashboard.INVENTORY);
			moneyLeft.setText(Integer.toString(money));
//			System.out.println(money);
			
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
		//Subtract money flow
		if( money >= 100){
			money -= 100;
			inventory.add("Baseball Bat");
			moneyLeft.setText(Integer.toString(money));
			Context context = getApplicationContext();
			CharSequence text = "Baseball bat has been purchased";
			int duration = Toast.LENGTH_SHORT;
			Toast.makeText(context, text, duration).show();
			//Remove button
			Button button = (Button) findViewById(R.id.button1);        
		    button.setOnClickListener(new OnClickListener() {
		        public void onClick(View view) {
//		        	if( money >= 100){
//		    			money -= 100;
//		    		inventory.add("Baseball Bat");
//		    		moneyLeft.setText(Integer.toString(money));
		            ViewGroup parentView = (ViewGroup) view.getParent();
		            parentView.removeView(view);
//		        	}
		        }
		    });
		}		
	}
	public void purchaseItem2(View view){
		//Add item to inventory
		//Subtract money flow
		if( money >= 200){
			money -= 200;
			inventory.add("Golf Club");
			moneyLeft.setText(Integer.toString(money));
			Context context = getApplicationContext();
			CharSequence text = "Golf club has been purchased";
			int duration = Toast.LENGTH_SHORT;
			Toast.makeText(context, text, duration).show();
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
		//Subtract money flow
		if( money >= 300){
			money -= 300;
			inventory.add("Mace");
			moneyLeft.setText(Integer.toString(money));
			Context context = getApplicationContext();
			CharSequence text = "Mace has been purchased";
			int duration = Toast.LENGTH_SHORT;
			Toast.makeText(context, text, duration).show();
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
		//Subtract money flow
		if( money >= 400){
			money -= 400;
			inventory.add("Flail");
			moneyLeft.setText(Integer.toString(money));
			Context context = getApplicationContext();
			CharSequence text = "Flail has been purchased";
			int duration = Toast.LENGTH_SHORT;
			Toast.makeText(context, text, duration).show();
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
		//Subtract money flow
		if( money >= 500){
			money -= 500;
			inventory.add("Crowbar");
			moneyLeft.setText(Integer.toString(money));
			Context context = getApplicationContext();
			CharSequence text = "Crowbar has been purchased";
			int duration = Toast.LENGTH_SHORT;
			Toast.makeText(context, text, duration).show();
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
