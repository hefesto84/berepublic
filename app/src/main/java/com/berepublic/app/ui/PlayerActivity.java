package com.berepublic.app.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.berepublic.app.R;
import com.berepublic.app.model.Song;
import com.facebook.drawee.view.SimpleDraweeView;

import org.parceler.Parcel;
import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dani on 9/5/16.
 */
public class PlayerActivity extends AppCompatActivity {

    private Song mSong;

    @Bind(R.id.imgSong)
    SimpleDraweeView imgSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        mSong = (Song) Parcels.unwrap(getIntent().getParcelableExtra("song"));
        ButterKnife.bind(this);

        load();
    }

    private void load(){
        imgSong.setImageURI(Uri.parse(mSong.getArtworkUrl100()));
    }

}
