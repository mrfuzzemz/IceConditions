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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    // URL Address
    String url = "http://www.neclimbs.com/?PageName=iceConditionsReport";
    ProgressDialog mProgressDialog;
    Calendar c = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
    String strDate = sdf.format(c.getTime());
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	    new Scrape().execute();
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
			    //TextView conditionsTextView = (TextView) findViewById(R.id.conditions);
			    // conditionsTextView.setText(currentConditions);
			    TextView statusTextView = (TextView) findViewById(R.id.status);
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
		    	
		    default:
		      return super.onOptionsItemSelected(item);
		  }
		}

	public String getConditions(){
	    TextView statusTextView = (TextView) findViewById(R.id.status);
	    statusTextView.setText(this.getString(R.string.updating));
	    //Document doc = null;
	    // Grab the web document
//	    try {
//			doc = Jsoup.connect("http://www.neclimbs.com/?PageName=iceConditionsReport").get();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//		    statusTextView.setText(this.getString(R.string.grabError));
//			e.printStackTrace();
//			return "";
//		}

//	    Elements spans = doc.select("span#textMain");
//	    
//	    return spans.toString();
	    new Scrape().execute();
		return "Current Conditions!";
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
	
	
	
//	private class conditionsUpdaterTask extends AsyncTask<String, Integer, String> {
//	    // Do the long-running work in here
//	    protected String doInBackground(String... urls) {
//	        int count = urls.length;
//	        long totalSize = 0;
//	        for (int i = 0; i < count; i++) {
//	            totalSize += 1;
//	            publishProgress((int) ((i / (float) count) * 100));
//	            // Escape early if cancel() is called
//	            if (isCancelled()) break;
//	        }
//	
//	        return String.valueOf(totalSize);
//	    }
//	
////	    // This is called each time you call publishProgress()
////	    protected void onProgressUpdate(Integer... progress) {
////	        setProgressPercent(progress[0]);
////	    }
////	
////	    // This is called when doInBackground() is finished
////	    protected void onPostExecute(Long result) {
////	        showNotification("Downloaded " + result + " bytes");
////	    }
//	}
}