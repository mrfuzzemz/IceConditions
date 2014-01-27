package com.mrfuzzemz.neclimbsiceconditions;

import java.io.IOException;



import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class MapActivity extends Activity {


	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.secondarymenu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		  switch (item.getItemId()) {
		    case R.id.returnmenuitem:
		    	//Intent mapIntent = new Intent(this, MainActivity.class);
		    	//startActivity(mapIntent);
		    	finish();
		    	return true;

		    	
		    default:
		      return super.onOptionsItemSelected(item);
		  }
		}

	
	
	

}