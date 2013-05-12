package com.example.huntersandzombiesdemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Inventory extends Activity {
	private TextView moneyLeft;
	private Button returnToDashBtn;
	private int money;
	private List<Boolean> inventory;
	private ParseUser currentUser;
	private ParseObject moneyObj;
	private TableLayout table, table2;
	private TableRow row0,row1,row2,row3,row4,row5,row6,row7,row8,row9,row10;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inventory);
		Parse.initialize(this, "LtZV0e5xH56B9pBgRv9PvzsXf2VM8t1sWPkOgsI3", "jDhUAqESu8KPfZLcfOIcb2cq6EaVmNiYE0W0H0XX");
		ParseAnalytics.trackAppOpened(getIntent());
		currentUser = ParseUser.getCurrentUser();
		moneyLeft = (TextView) findViewById(R.id.moneyLeft);
		table = (TableLayout) findViewById(R.id.tableLayout1);
		table2 = (TableLayout) findViewById(R.id.tableLayout2);
		row0 = (TableRow) findViewById(R.id.tableRow0);
		row1 = (TableRow) findViewById(R.id.tableRow1);
		row2 = (TableRow) findViewById(R.id.tableRow2);
		row3 = (TableRow) findViewById(R.id.tableRow3);
		row4 = (TableRow) findViewById(R.id.tableRow4);
		row5 = (TableRow) findViewById(R.id.tableRow5);
		row6 = (TableRow) findViewById(R.id.tableRow6);
		row7 = (TableRow) findViewById(R.id.tableRow7);
		row8 = (TableRow) findViewById(R.id.tableRow8);
		row9 = (TableRow) findViewById(R.id.tableRow9);
		row10 = (TableRow) findViewById(R.id.tableRow10);

		table.removeAllViews();
		table2.removeViews(2, 5);
		returnToDashBtn = (Button)findViewById(R.id.inventoryToDashbtn);
		returnToDashBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				 Bundle bundle = new Bundle();
//				 bundle.putInt(Dashboard.USER_MONEY, money);
//				 bundle.pu(Dashboard.INVENTORY, inventory);
				 Intent intent = new Intent(Inventory.this, Dashboard.class);
//				 intent.putExtras(bundle);
				 setResult(RESULT_OK, intent);
				 startActivity(intent);
				 finish();
			}
			
		});
		
//		final Bundle extras = getIntent().getExtras();
//		if(extras!=null){
			
//			money = extras.getInt(Dashboard.USER_MONEY);
//			System.out.println(money);
			
//		}


		ParseQuery query = new ParseQuery("Money");
		query.whereEqualTo("username", currentUser);
		query.findInBackground(new FindCallback(){

			public void done(List<ParseObject> object, ParseException e){
				if (object != null && object.size() > 0) {
					for (ParseObject obj : object) {
						moneyObj = obj;
					}
				} else {
					moneyObj = new ParseObject("Money");
					moneyObj.put("username",currentUser);
					moneyObj.put("money",150);
					moneyObj.put("inventory",Arrays.asList(false,false,false,false,false));
					moneyObj.saveInBackground();
				}
				money = moneyObj.getInt("money");
				inventory = moneyObj.getList("inventory");
//				inventory = extras.getStringArrayList(Dashboard.INVENTORY);
				moneyLeft.setText(Integer.toString(money));
				if(inventory.get(0)){
					table2.addView(row6);
				}
				else{
					table.addView(row1);
				}
				if(inventory.get(1)){
					table2.addView(row7);
				}
				else{
					table.addView(row2);
				}
				if(inventory.get(2)){
					table2.addView(row8);
				}
				else{
					table.addView(row3);
				}
				if(inventory.get(3)){
					table2.addView(row9);
				}
				else{
					table.addView(row4);
				}
				if(inventory.get(4)){
					table2.addView(row10);
				}
				else{
					table.addView(row5);
				}
				
				//				inventory = moneyObj.get
				
			}});
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inventory, menu);
		return true;
	}
	public void purchaseItem1(View view){
		if( money >= 100){
			money -= 100;
			ParseQuery query = new ParseQuery("Money");
			query.whereEqualTo("username", currentUser);
			query.findInBackground(new FindCallback(){
				public void done(List<ParseObject> object, ParseException e){
					if (object != null && object.size() > 0) {
						for (ParseObject obj : object) {
							moneyObj = obj;
						}
					}
					moneyObj.put("money", money);
					inventory.set(0,true);					
					moneyObj.put("inventory", inventory);
					moneyObj.saveInBackground();
					moneyLeft.setText(Integer.toString(money));
					Context context = getApplicationContext();
					CharSequence text = "Baseball bat has been purchased";
					int duration = Toast.LENGTH_SHORT;
					Toast.makeText(context, text, duration).show();
					//Remove button
					Button button = (Button) findViewById(R.id.button1);        
				    button.setOnClickListener(new OnClickListener() {
				        public void onClick(View view) {
				            ViewGroup parentView = (ViewGroup) view.getParent();
				            parentView.removeView(view);
//				        	}
				        }
				    });
					
				}});
			
		}		
	}
	public void purchaseItem2(View view){
		//Add item to inventory
		//Subtract money flow
		if( money >= 200){
			money -= 200;
			ParseQuery query = new ParseQuery("Money");
			query.whereEqualTo("username", currentUser);
			query.findInBackground(new FindCallback(){
				public void done(List<ParseObject> object, ParseException e){
					if (object != null && object.size() > 0) {
						for (ParseObject obj : object) {
							moneyObj = obj;
						}
					}
					moneyObj.put("money", money);
					inventory.set(1,true);					
					moneyObj.put("inventory", inventory);
					moneyObj.saveInBackground();					
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
					
				}});
		}
	}
	public void purchaseItem3(View view){
		//Add item to inventory
		//Subtract money flow
		if( money >= 300){
			money -= 300;
			ParseQuery query = new ParseQuery("Money");
			query.whereEqualTo("username", currentUser);
			query.findInBackground(new FindCallback(){
				public void done(List<ParseObject> object, ParseException e){
					if (object != null && object.size() > 0) {
						for (ParseObject obj : object) {
							moneyObj = obj;
						}
					}
					moneyObj.put("money", money);
					inventory.set(2,true);					
					moneyObj.put("inventory", inventory);
					moneyObj.saveInBackground();
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
				}});

		}
	}
	public void purchaseItem4(View view){
		//Add item to inventory
		//Subtract money flow
		if( money >= 400){
			money -= 400;
			ParseQuery query = new ParseQuery("Money");
			query.whereEqualTo("username", currentUser);
			query.findInBackground(new FindCallback(){
				public void done(List<ParseObject> object, ParseException e){
					if (object != null && object.size() > 0) {
						for (ParseObject obj : object) {
							moneyObj = obj;
						}
					}
					moneyObj.put("money", money);
					inventory.set(3,true);
					moneyObj.put("inventory", inventory);
					moneyObj.saveInBackground();					
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
				}});

		}
	}
	public void purchaseItem5(View view){
		//Add item to inventory
		//Subtract money flow
		if( money >= 500){
			money -= 500;
			ParseQuery query = new ParseQuery("Money");
			query.whereEqualTo("username", currentUser);
			query.findInBackground(new FindCallback(){
				public void done(List<ParseObject> object, ParseException e){
					if (object != null && object.size() > 0) {
						for (ParseObject obj : object) {
							moneyObj = obj;
						}
					}
					moneyObj.put("money", money);
					inventory.set(4,true);					
					moneyObj.put("inventory", inventory);
					moneyObj.saveInBackground();					
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
				}});

		}
	}	
	

}
