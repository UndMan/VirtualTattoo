package com.example.manuel.virtualtattoo;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import android.content.*;
import android.widget.Toast;

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
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Format;

import static com.example.manuel.virtualtattoo.R.id.imageView;
import static com.example.manuel.virtualtattoo.R.id.text_sketch;

public class MainScreen extends AppCompatActivity {
    private AdView mAdView;
    private ImageButton searchButton;
    private EditText searchText;
    private RequestQueue queue;
    private TextView description;
    public static final String SEARCHTEXT = "searchText";

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

        searchButton = (ImageButton) findViewById(R.id.filterButton);
        searchText = (EditText) findViewById(R.id.searchText);

      //  queue = Volley.newRequestQueue(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        SharedPreferences settings = getSharedPreferences(SEARCHTEXT, 0);
        String prefSearchText = settings.getString("searchText", "");
        searchText.setText(prefSearchText);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textsearch = searchText.getText().toString().trim();


               if(!textsearch.isEmpty()) {
                   Intent intent = new Intent(MainScreen.this, ResultsActivity.class);
                   intent.putExtra(ResultsActivity.SEARCHTEXT, textsearch);
                   startActivity(intent);
               }
               else
                   Toast.makeText(getBaseContext(), "Search text to short", Toast.LENGTH_LONG).show();

                /*String url = Uri.parse("https://api.flickr.com/services/rest/")
                        .buildUpon()
                        .appendQueryParameter("method", "flickr.photos.search")
                        .appendQueryParameter("api_key", "5bcfbcf3ea86ca9a1d0e2607bb066478")
                        .appendQueryParameter("text", "tattoo " + searchText.getText())
                        .appendQueryParameter("per_page", "9")
                        .appendQueryParameter("page", "1")
                        .appendQueryParameter("format", "json")
                        .appendQueryParameter("nojsoncallback", "1")
                        .build()
                        .toString();
                Log.i("MUn", "Text="+searchText.getText().toString());
                StringRequest request = new StringRequest(
                        Request.Method.GET,
                        url,
                        new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                Gson gson = new Gson();
                                FlickrData data = gson.fromJson(response, FlickrData.class);
                                int i = 1;

                                for (Photo p : data.getPhotos().getPhoto()) {
                                    //https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{o-secret}_o.(jpg|gif|png)
                                    String photoURL = "https://farm" + p.getFarm() + ".staticflickr.com/" + p.getServer() + "/" + p.getId() + "_" + p.getSecret() + ".jpg";
                                    Uri uri = Uri.parse(photoURL);

                                    int id = getResources().getIdentifier("owl" + (i), "id", "com.example.manuel.virtualtattoo");

                                    int iddesc = getResources().getIdentifier("desc" + (i), "id", "com.example.manuel.virtualtattoo");

                                    final ImageView display = (ImageView) findViewById(id);
                                    TextView description = (TextView) findViewById(iddesc);

                                    description.setText(p.getTitle());

                                    Picasso.with(MainScreen.this)
                                            .load(uri)
                                            .into(display);
                                    i++;
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.i("TEST", "ERROR");
                            }
                        }
                );
                queue.add(request);*/
            }
        });

        Log.i("MUn", "onCreate");

    }

    @Override
    protected void onStart() {
        searchButton.callOnClick();
        Log.i("MUn", "onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i("MUn", "onStop");
        super.onStop();

        SharedPreferences settings = getSharedPreferences(SEARCHTEXT, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("searchText", searchText.getText().toString());

        // Commit the edits!
        editor.commit();
    }

    @Override
    protected void onRestart() {
        Log.i("MUn", "onRestart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.i("MUn", "onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.i("MUn", "onPause");
        super.onPause();
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
