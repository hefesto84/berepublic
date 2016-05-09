package com.berepublic.app.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.berepublic.app.R;
import com.berepublic.app.adapter.SongAdapter;

public class MainActivity extends AppCompatActivity {

    private SongAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
