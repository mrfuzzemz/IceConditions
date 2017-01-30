package com.mrfuzzemz.neclimbsiceconditions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
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


public class MapActivity extends Activity implements OnMapReadyCallback {

    static final LatLng CATHEDRAL = new LatLng(44.065817, -71.165033);
    static final LatLng FRANKENSTEIN = new LatLng(44.156033, -71.3668);
    static final LatLng HAMBURG = new LatLng(53.558, 9.927);
    static final LatLng KIEL = new LatLng(53.551, 9.993);
    private GoogleMap map;

    Map<String, LatLng> mapLatLng = new HashMap<String, LatLng>(){{
        put("North End Cathedral", new LatLng(44.065817, -71.165033));
        put("Frankenstein", new LatLng(44.156033, -71.3668));
        put("Arethusa Falls", new LatLng(44.147515, -71.385296));
        put("Lake Willoughby", new LatLng(44.724290, -72.030079));
        put("Texaco", new LatLng(44.12981,-71.34951));
        put("Champney Falls", new LatLng(43.978787, -71.287982));
        put("Kinsman Notch", new LatLng(44.029230, -71.766180));
        put("Trollville", new LatLng(44.141241, -71.192637));
        put("The Black Dike", new LatLng(44.156477, -71.687177));
        put("The Flume", new LatLng(44.101520, -71.657760));
        put("Elephant Head Gully", new LatLng(44.214615, -71.406231));

    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        MapFragment mapFragment = ((MapFragment) getFragmentManager().findFragmentById(R.id.locationsMap));
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        //map.addMarker(new MarkerOptions()
        //      .position(new LatLng(0, 0))
        //    .title("Marker"));
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.setMyLocationEnabled(true);
        map.setTrafficEnabled(false);
        map.setIndoorEnabled(false);
        map.setBuildingsEnabled(false);
        map.getUiSettings().setZoomControlsEnabled(true);


        //Marker kiel = map.addMarker(new MarkerOptions()
          //      .position(FRANKENSTEIN)
            //    .title("Frankenstein")
              //  .snippet("Frankenstein is cool")
                //.icon(BitmapDescriptorFactory
                  //      .fromResource(R.drawable.ic_launcher)));
        for (Map.Entry<String, LatLng> entry : mapLatLng.entrySet())
        {
            Marker iceMarker = map.addMarker( new MarkerOptions().position(entry.getValue())
                    .title(entry.getKey()));
                    // Probably want to add verdict as a snippet here.
                    // probably and .icon as well
        }



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