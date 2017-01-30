package com.mrfuzzemz.neclimbsiceconditions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    DBHelper myDb;
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
        myDb = new DBHelper(this);

        // May want to prepopulate DB with names and lat/long here.
        // myDb.insertData("Frankenstein", "OK", "OK", "Today", 44.156033, -71.3668, "picture" );
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
                /* Pattern for pulling out location condition details */
                Pattern areaPattern = Pattern.compile("(.+)\\s([A-Z]{2,5}?)\\s(.+)");//(.+width=\"80\" align=\"center\">)(Mar)(.+</td>.+)");
                Pattern picPattern = Pattern.compile("(.+) src=\"(.+)\" alt(.+)");
                // Would it make sense to pull out of a lookup table? Either the location or the
                // status?


                // So far group 1 is the area name, group 2 is the verdict, group 3 is the date
                //
                // May want to change the pattern to textPattern, and make a separate pattern
                // for grabbing out the image

                //

                //
                //






                // Connect to the web site
                Document document = Jsoup.connect(url).get();

                // Get the html document title
                // title = document.title();
                Elements spans = document.select(".iceReportBlurbBlock");
                for (Element span : spans) {
                    if (span.text() != null)
                        title = title + span.text() + "\n\n";
                }

                Elements spans2 = document.select(".iceReportText");
                // Want to make this more sophisticated
                // Best to have data structure for location with status, date, and photo, location on map!
                for (Element span : spans2) {
                    if (span.text() != null) {
                        /* For each iceReportText block pick out and store the details */
                        title = title + span.text() + "\n";
                        // Matcher areaMatch = areaPattern.matcher(span.html());
                        Matcher areaMatch = areaPattern.matcher(span.text());
                        //
                        // Nice for debugging patterns
                        //
                        // title = title + "\n\n\n\n\n" + span.html() + "\n\n\n\n\n";
                        //

                        if (areaMatch.find()) {
                        /* TODO: Why not just regex the text we already have, and grab
                        *        the photo details from the html? */

                            title = title + areaMatch.group(1) + "\n" + areaMatch.group(2) + "\n" + areaMatch.group(3) + "\n";
                            // title = title + areaMatch.group(1) + areaMatch.group(2) + areaMatch.group(3) + "\n";

                            /* Add this areas details to the database */
                            myDb.updateData(areaMatch.group(1),"NA",areaMatch.group(2),areaMatch.group(3),"http://the.pic");

                        }
                        Matcher picMatch = picPattern.matcher(span.html());
                        if (picMatch.find()) {
                            title = title + picMatch.group(2).replaceFirst("images/", "") + "\n";
                            if (!picMatch.group(2).equals("@string/img_path")) {
                                //
                                // This is where we add storing the image somehow
                                //
                                // This is either as a blob in the DB or as a path to the
                                // image saved in the storage.
                                //


                            }

                        }
                        /* TODO: Use a lookup table for picking what area each climbing area is in */
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


