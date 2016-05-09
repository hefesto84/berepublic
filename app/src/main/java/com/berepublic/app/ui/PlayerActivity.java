package com.berepublic.app.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.berepublic.app.R;
import com.berepublic.app.model.Song;

import org.parceler.Parcel;
import org.parceler.Parcels;

/**
 * Created by dani on 9/5/16.
 */
public class PlayerActivity extends AppCompatActivity {

    private Song mSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        mSong = (Song) Parcels.unwrap(getIntent().getParcelableExtra("song"));
    }

}
