package com.example.manuel.virtualtattoo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by Manuel on 01.06.2017.
 */

public class Sketch extends AppCompatActivity {
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sketch);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(mToolbar);

        MobileAds.initialize(getApplicationContext(),
                "ca-app-pub-3940256099942544~3347511713");
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
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
            intent = new Intent(Sketch.this, Studios.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.myCollection){
            intent = new Intent(Sketch.this, MyCollection.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.browseDesigns){
            intent = new Intent(Sketch.this, MainScreen.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.vrTattoo){
            intent = new Intent(Sketch.this, VRTattoo.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.imports){
            intent = new Intent(Sketch.this, Import.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.help){
            intent = new Intent(Sketch.this, Help.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.options){
            intent = new Intent(Sketch.this, Options.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.about){
            intent = new Intent(Sketch.this, About.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.logout){
            intent = new Intent(Sketch.this, LoginScreen.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.sketch) {
            /*intent = new Intent(Sketch.this, Sketch.class);
            startActivity(intent);*/
        }

        return super.onOptionsItemSelected(item);
    }
}
