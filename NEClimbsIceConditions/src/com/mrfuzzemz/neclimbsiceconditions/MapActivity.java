package com.mrfuzzemz.neclimbsiceconditions;

import java.io.IOException;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.MapFragment;




import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class MapActivity extends Activity {
	
	static final LatLng CATHEDRAL = new LatLng(44.065817,-71.165033);
	static final LatLng FRANKENSTEIN = new LatLng(44.156033,-71.3668); 
	static final LatLng HAMBURG = new LatLng(53.558, 9.927);
	static final LatLng KIEL = new LatLng(53.551, 9.993);
	private GoogleMap map;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
	    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.locationsMap))
	            .getMap();
	        Marker hamburg = map.addMarker(new MarkerOptions().position(CATHEDRAL)
	            .title("Cathedral Ledge"));
	        Marker kiel = map.addMarker(new MarkerOptions()
	            .position(FRANKENSTEIN)
	            .title("Frankenstein")
	            .snippet("Frankenstein is cool")
	            .icon(BitmapDescriptorFactory
	                .fromResource(R.drawable.ic_launcher)));

	        // Move the camera instantly to hamburg with a zoom of 15.
	        map.moveCamera(CameraUpdateFactory.newLatLngZoom(CATHEDRAL, 15));

	        // Zoom in, animating the camera.
	        map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
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