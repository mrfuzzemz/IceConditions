package com.mrfuzzemz.neclimbsiceconditions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by matthias on 5/21/16.
 */
public class DbviewActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbview);

        TextView link = (TextView) findViewById(R.id.nelink);
        String linkText = "Visit the <a href='http://www.neclimbs.com/?PageName=iceConditionsReport'>NEClimbs</a> web page.";
        link.setText(Html.fromHtml(linkText));
        link.setMovementMethod(LinkMovementMethod.getInstance());
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