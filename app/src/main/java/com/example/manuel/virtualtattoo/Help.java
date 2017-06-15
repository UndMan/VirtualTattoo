package com.example.manuel.virtualtattoo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by Manuel on 01.06.2017.
 */

public class Help extends AppCompatActivity {
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(mToolbar);

        MobileAds.initialize(getApplicationContext(),
                "ca-app-pub-3940256099942544~3347511713");
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        TextView body = (TextView) findViewById(R.id.body);
        body.setMovementMethod(new ScrollingMovementMethod());
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        if(item.getItemId() == R.id.nearbyStudios){
            intent = new Intent(Help.this, Studios.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.myCollection){
            intent = new Intent(Help.this, MyCollection.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.browseDesigns){
            intent = new Intent(Help.this, MainScreen.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.vrTattoo){
            intent = new Intent(Help.this, VRTattoo.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.imports){
            intent = new Intent(Help.this, Import.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.help){
            /*intent = new Intent(Help.this, Help.class);
            startActivity(intent);*/
        }else if(item.getItemId() == R.id.options){
            intent = new Intent(Help.this, Options.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.about){
            intent = new Intent(Help.this, About.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.logout){
            intent = new Intent(Help.this, LoginScreen.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.sketch) {
            intent = new Intent(Help.this, Sketch.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mMenuInflater = getMenuInflater();
        mMenuInflater.inflate(R.menu.menu, menu);
        return true;
    }
}
