package com.mrfuzzemz.neclimbsiceconditions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    // URL Address
    String url = "http://www.neclimbs.com/?PageName=iceConditionsReport";
    ProgressDialog mProgressDialog;
    Calendar c = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd:MMMM:yyyy HH:mm a");
    String strDate = sdf.format(c.getTime());
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//TextView conditionsText = (TextView) findViewById(R.id.conditions);
		//String conditionsString = conditionsText.getText().toString();
		//if (conditionsString.contentEquals("")){ 
	    	new Scrape().execute();
	    	TextView statusTextView = (TextView) findViewById(R.id.status);
	    	statusTextView.setText("Updated " + strDate);
		//}
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
			    new Scrape().execute();
			    TextView statusTextView = (TextView) findViewById(R.id.status);
			    Calendar c = Calendar.getInstance();
			    strDate = sdf.format(c.getTime());
			    statusTextView.setText("Updated " + strDate);
		      return true;
		    case R.id.action_settings:
		    	Context context = getApplicationContext();
		    	CharSequence text = "No settings yet";
		    	int duration = Toast.LENGTH_SHORT;
		    	
		    	Toast toast = Toast.makeText(context, text, duration);
		    	toast.show();
		    	
		    	return true;
		    case R.id.about:
		    	Intent myIntent = new Intent(this, AboutActivity.class);
		    	startActivity(myIntent);
		    	return true;
		    case R.id.map:
		    	Intent mapIntent = new Intent(this, MapActivity.class);
		    	startActivity(mapIntent);
		    	return true;
		    default:
		      return super.onOptionsItemSelected(item);
		  }
		}




    // Title AsyncTask
    private class Scrape extends AsyncTask<Void, Void, Void> {
        String title = "";
        Document document;
 
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("NEClimbs Ice Conditions");
            mProgressDialog.setMessage("Updating...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }
 
        @Override
        protected Void doInBackground(Void... params) {
            try {
                // Connect to the web site
                Document document = Jsoup.connect(url).get();
                // Get the html document title
                // title = document.title();
        	    Elements spans = document.select(".iceReportBlurbBlock");
        	    for (Element span : spans){
        	    	if (span.text() != null)
        	    		title = title + span.text() + "\n\n";
        	    }
        	    Elements spans2 = document.select(".iceReportText");
        	    for (Element span : spans2){
        	    	if (span.text() != null){
        	    		title = title + span.text() + "\n";
        	    	}
        	    }
        	    //title = spans.text();
        	    
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
 
        @Override
        protected void onPostExecute(Void result) {
            // Set title into TextView
            TextView txttitle = (TextView) findViewById(R.id.conditions);
            txttitle.setText(title);
    	    
            mProgressDialog.dismiss();
        }
    }

}