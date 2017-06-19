package com.example.manuel.virtualtattoo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

public class LoginScreen extends AppCompatActivity {
    private EditText editUsername;
    private EditText editPassword;
    public static final String USERPW = "userPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        editPassword = (EditText) findViewById(R.id.EditPassword);
        editUsername = (EditText) findViewById(R.id.EditUsername);

        SharedPreferences settings = getSharedPreferences(USERPW, 0);
        String user = settings.getString("username", "");
        String pw = settings.getString("password", "");
        editUsername.setText(user);
        editPassword.setText(pw);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(LoginScreen.this, MainScreen.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        Log.i("MUn", "onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i("MUn", "onStop");
        super.onStop();

        SharedPreferences settings = getSharedPreferences(USERPW, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("username", editUsername.getText().toString());
        editor.putString("password", editPassword.getText().toString());

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
}