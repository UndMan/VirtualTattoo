package com.example.manuel.virtualtattoo;


import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by Manuel on 01.06.2017.
 */

public class VRTattoo extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vrtattoo);
        MobileAds.initialize(getApplicationContext(),
                "ca-app-pub-3940256099942544~3347511713");
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(mToolbar);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sketch_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mMenuInflater = getMenuInflater();
        mMenuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        if(item.getItemId() == R.id.nearbyStudios){
            intent = new Intent(VRTattoo.this, Studios.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.myCollection){
            intent = new Intent(VRTattoo.this, MyCollection.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.browseDesigns){
            intent = new Intent(VRTattoo.this, MainScreen.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.vrTattoo){
            /*intent = new Intent(VRTattoo.this, VRTattoo.class);
            startActivity(intent);*/
        }else if(item.getItemId() == R.id.imports){
            intent = new Intent(VRTattoo.this, Import.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.help){
            intent = new Intent(VRTattoo.this, Help.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.options){
            intent = new Intent(VRTattoo.this, Options.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.about){
            intent = new Intent(VRTattoo.this, About.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.logout){
            intent = new Intent(VRTattoo.this, LoginScreen.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.sketch) {
            intent = new Intent(VRTattoo.this, Sketch.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
    public void onItemSelected(AdapterView<?> parent, View view,
                              int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}
