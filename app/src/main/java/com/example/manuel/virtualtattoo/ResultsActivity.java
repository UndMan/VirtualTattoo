package com.example.manuel.virtualtattoo;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.manuel.virtualtattoo.DB.DBRepository;
import com.example.manuel.virtualtattoo.Interface.PhotoHandler;
import com.example.manuel.virtualtattoo.dto.FlickrData;
import com.example.manuel.virtualtattoo.dto.Photo;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ResultsActivity extends AppCompatActivity {
    private DBRepository dbRepository;

    private String searchText;


    private AdView mAdView;
    private RequestQueue queue;
    private TextView description;


    public static final String SEARCHTEXT = "searchText";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);


        MobileAds.initialize(getApplicationContext(),
                "ca-app-pub-3940256099942544~3347511713");
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        queue = Volley.newRequestQueue(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        SharedPreferences settings = getSharedPreferences(SEARCHTEXT, 0);
        String prefSearchText = settings.getString("searchText", "");


        Intent intent = getIntent();
        searchText = intent.getStringExtra(SEARCHTEXT);

        dbRepository = new DBRepository(this);

        String url = Uri.parse("https://api.flickr.com/services/rest/")
                .buildUpon()
                .appendQueryParameter("method", "flickr.photos.search")
                .appendQueryParameter("api_key", "5bcfbcf3ea86ca9a1d0e2607bb066478")
                .appendQueryParameter("text", "tattoo " + searchText)
                .appendQueryParameter("per_page", "9")
                .appendQueryParameter("page", "1")
                .appendQueryParameter("format", "json")
                .appendQueryParameter("nojsoncallback", "1")
                .build()
                .toString();
        Log.i("MUn", "Text="+searchText);
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        FlickrData data = gson.fromJson(response, FlickrData.class);
                        int i = 1;

                        dbRepository.InsertPhotoData(searchText, data.getPhotos().getPhoto());

                        for (Photo p : data.getPhotos().getPhoto()) {
                            //https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{o-secret}_o.(jpg|gif|png)
                            String photoURL = "https://farm" + p.getFarm() + ".staticflickr.com/" + p.getServer() + "/" + p.getId() + "_" + p.getSecret() + ".jpg";
                            Uri uri = Uri.parse(photoURL);

                            int id = getResources().getIdentifier("owl" + (i), "id", "com.example.manuel.virtualtattoo");

                            int iddesc = getResources().getIdentifier("desc" + (i), "id", "com.example.manuel.virtualtattoo");

                            final ImageView display = (ImageView) findViewById(id);
                            TextView description = (TextView) findViewById(iddesc);

                            description.setText(p.getTitle());

                            Picasso.with(ResultsActivity.this)
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

        queue.add(request);



    }





}
