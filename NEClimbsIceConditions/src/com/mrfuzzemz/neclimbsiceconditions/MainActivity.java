package com.mrfuzzemz.neclimbsiceconditions;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		  switch (item.getItemId()) {
		    case R.id.update:
				String currentConditions = getConditions();
			    TextView conditionsTextView = (TextView) findViewById(R.id.conditions);
			    conditionsTextView.setText(currentConditions);
			    TextView statusTextView = (TextView) findViewById(R.id.status);
			    statusTextView.setText("");
		      return true;
		    default:
		      return super.onOptionsItemSelected(item);
		  }
		}

	public String getConditions(){
	    TextView statusTextView = (TextView) findViewById(R.id.status);
	    statusTextView.setText(this.getString(R.string.updating));
	    Document doc = null;
	    // Grab the web document
	    try {
			doc = Jsoup.connect("http://www.neclimbs.com/?PageName=iceConditionsReport").get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		    statusTextView.setText(this.getString(R.string.grabError));
			e.printStackTrace();
			return "";
		}

	    Elements spans = doc.select("span#textMain");
	    
	    return spans.toString();
		// return "Current Conditions!";
	}

}