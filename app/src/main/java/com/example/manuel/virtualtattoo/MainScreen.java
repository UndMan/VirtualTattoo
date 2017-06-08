package com.example.manuel.virtualtattoo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import android.content.*;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.manuel.virtualtattoo.dto.FlickrData;
import com.example.manuel.virtualtattoo.dto.Photo;
import com.example.manuel.virtualtattoo.dto.Photos;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.Gson;

import java.text.Format;

public class MainScreen extends AppCompatActivity {
    private AdView mAdView;
    private ImageView owl1;
    private ImageButton searchButton;
    private EditText searchText;
    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(mToolbar);

        MobileAds.initialize(getApplicationContext(),
                "ca-app-pub-3940256099942544~3347511713");
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        owl1 = (ImageView) findViewById(R.id.owl1);
        searchButton = (ImageButton) findViewById(R.id.filterButton);
        searchText = (EditText) findViewById(R.id.searchText);

        queue = Volley.newRequestQueue(this);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = Uri.parse("https://api.flickr.com/services/rest/")
                        .buildUpon()
                        .appendQueryParameter("method","flickr.photos.search")
                        .appendQueryParameter("api_key", "5bcfbcf3ea86ca9a1d0e2607bb066478")
                        .appendQueryParameter("text", "tattoo")
                        .appendQueryParameter("per_page", "9")
                        .appendQueryParameter("page", "1")
                        .appendQueryParameter("format", "json")
                        .appendQueryParameter("nojsoncallback", "1")
                        .build()
                        .toString();
                Log.i("TEST", url);

                StringRequest request = new StringRequest(
                        Request.Method.GET,
                        url,
                        new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                Log.i("TEST",response);
                                Gson gson = new Gson();
                                FlickrData data = gson.fromJson(response, FlickrData.class);
                                int i = 1;
                                for(Photo p : data.getPhotos().getPhoto()){
                                    //https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{o-secret}_o.(jpg|gif|png)
                                    String photoURL = "https://farm"+p.getFarm()+".staticflickr.com/"+p.getServer()+"/"+p.getId()+"_"+p.getSecret()+".jpg";


                                    Uri uri = Uri.parse(photoURL);
                                    Log.i("TEST", photoURL);

                                    int id = getResources().getIdentifier("owl"+(i), "id", "com.example.manuel.virtualtattoo");
                                    ImageView display = (ImageView) findViewById(id);
                                    display.setImageURI(uri);


                                    /****************************
                                     * Lisa es funktioniert soweit, nur die Bilder werden im ImageView nicht dargestellt.
                                     * Das liegt irgendwie an der Größe der Bilder und das setImageURI(uri) funktioniert nicht.
                                     * Da braucht man eine Bitmap und muss die Größe anpassen, da ImageView keine großen Bilder darstellen kann.
                                     * Bitte sieh dir das noch an.
                                     * Im Android Monitor gibt er die Links zu den Bilder aus. Es funktioniert also die API nur nicht das darstellen in der App.
                                     * lg
                                     *
                                     * PS: Du kannst noch machen dass, wenn man was in das EditText eingibt und den Suchbutton klickt das die gewünschten Bilder kommen.
                                     * Zurzeit wir auch erst die Bilder geladen, wenn man auf den ImageButton klickt
                                     ***************************/

                                    i++;
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.i("TEST","ERROR");
                            }
                        }
                );
                queue.add(request);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mMenuInflater = getMenuInflater();
        mMenuInflater.inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        if(item.getItemId() == R.id.nearbyStudios){
            intent = new Intent(MainScreen.this, Studios.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.myCollection){
                intent = new Intent(MainScreen.this, MyCollection.class);
                startActivity(intent);
        }else if(item.getItemId() == R.id.browseDesigns){
                /*intent = new Intent(MainScreen.this, MainScreen.class);
                startActivity(intent);*/
        }else if(item.getItemId() == R.id.vrTattoo){
            intent = new Intent(MainScreen.this, VRTattoo.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.imports){
            intent = new Intent(MainScreen.this, Import.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.help){
            intent = new Intent(MainScreen.this, Help.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.options){
            intent = new Intent(MainScreen.this, Options.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.about){
            intent = new Intent(MainScreen.this, About.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.logout){
            intent = new Intent(MainScreen.this, LoginScreen.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.sketch) {
            intent = new Intent(MainScreen.this, Sketch.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}
