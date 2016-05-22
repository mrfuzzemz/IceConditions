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


        TextView text_col1 = (TextView) findViewById(R.id.textView);
        String col1Text = "Column 1";
        text_col1.setText(col1Text);

        TextView text_col2 = (TextView) findViewById(R.id.textView2);
        String col2Text = "Column 2";
        text_col2.setText(col2Text);

        TextView text_col3 = (TextView) findViewById(R.id.textView3);
        String col3Text = "Column 3";
        text_col3.setText(col3Text);

        TextView text_col4 = (TextView) findViewById(R.id.textView4);
        String col4Text = "Column 4";
        text_col4.setText(col4Text);
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
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}