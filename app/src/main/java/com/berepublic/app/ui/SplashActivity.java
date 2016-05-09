package com.berepublic.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.berepublic.app.R;

/**
 * Created by dani on 9/5/16.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
        	public void run() {
        		initApplication();
        	}
        }, 1500);
    }

    private void initApplication(){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
