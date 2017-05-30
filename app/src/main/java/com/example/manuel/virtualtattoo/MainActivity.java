package com.example.manuel.virtualtattoo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String EXTRA_MESSAGE = "com.example.manuel.virtualtattoo.LOGINSCREEN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view) {
            Intent intent = new Intent(MainActivity.this, LoginScreen.class);
            startActivity(intent);
    }
}
