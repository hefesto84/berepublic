package com.berepublic.app.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.berepublic.app.R;
import com.berepublic.app.model.Song;
import com.facebook.drawee.view.SimpleDraweeView;

import org.parceler.Parcel;
import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dani on 9/5/16.
 */
public class PlayerActivity extends AppCompatActivity {

    @Bind(R.id.imgSong)         SimpleDraweeView imgSong;
    @Bind(R.id.txtBandName)     TextView txtBandName;
    @Bind(R.id.txtSongName)     TextView txtSongName;
    @Bind(R.id.btnFastForward)  ImageButton btnFastForward;
    @Bind(R.id.btnNext)         ImageButton btnNext;
    @Bind(R.id.btnPause)        ImageButton btnPause;
    @Bind(R.id.btnPlay)         ImageButton btnPlay;
    @Bind(R.id.btnPrevious)     ImageButton btnPrevious;
    @Bind(R.id.btnRewind)       ImageButton btnRewind;

    private Song mSong;
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        mSong = (Song) Parcels.unwrap(getIntent().getParcelableExtra("song"));

        ButterKnife.bind(this);

        load();
    }

    private void load(){
        imgSong.setImageURI(Uri.parse(mSong.artworkUrl100));
        txtBandName.setText(mSong.artistName);
        txtSongName.setText(mSong.trackName);
    }

    @OnClick({R.id.btnFastForward, R.id.btnNext, R.id.btnPause, R.id.btnPlay, R.id.btnPrevious, R.id.btnRewind})
    public void OnButtonClick(View view){
        switch (view.getId()){
            case R.id.btnFastForward:
                break;
            case R.id.btnNext:
                break;
            case R.id.btnPause:
                pause();
                break;
            case R.id.btnPlay:
                play();
                break;
            case R.id.btnPrevious:
                break;
            case R.id.btnRewind:
                break;
        }
    }

    private void pause(){
        btnPlay.setVisibility(View.VISIBLE);
        btnPause.setVisibility(View.GONE);
    }

    private void play(){
        btnPlay.setVisibility(View.GONE);
        btnPause.setVisibility(View.VISIBLE);
    }
}
