package com.berepublic.app.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.berepublic.app.R;
import com.berepublic.app.model.Playlist;
import com.berepublic.app.model.Song;
import com.berepublic.app.service.AudioService;
import com.berepublic.app.utils.Constants;
import com.facebook.drawee.view.SimpleDraweeView;

import org.parceler.Parcel;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

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

    private Playlist mPlaylist;
    private boolean isPlaying = false;
    private AudioService mAudioService;
    private Intent mPlayerIntent;

    @Override
    protected void onStart() {
        super.onStart();
        if(mPlayerIntent==null){
            mPlayerIntent = new Intent(this, AudioService.class);
            bindService(mPlayerIntent, audioServiceConnection, Context.BIND_AUTO_CREATE);
            startService(mPlayerIntent);
            Log.d(Constants.TAG,"Player intent: "+mPlayerIntent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        mPlaylist = (Playlist)Parcels.unwrap(getIntent().getParcelableExtra("playlist"));

        ButterKnife.bind(this);

        load();
    }

    @Override
    protected void onDestroy() {
        stopService(mPlayerIntent);
        mAudioService=null;
        unbindService(audioServiceConnection);
        super.onDestroy();
    }

    private void load(){
        imgSong.setImageURI(Uri.parse(mPlaylist.songs.get(mPlaylist.currentSong).artworkUrl100));
        txtBandName.setText(mPlaylist.songs.get(mPlaylist.currentSong).artistName);
        txtSongName.setText(mPlaylist.songs.get(mPlaylist.currentSong).trackName);
    }

    @OnClick({R.id.btnFastForward, R.id.btnNext, R.id.btnPause, R.id.btnPlay, R.id.btnPrevious, R.id.btnRewind})
    public void OnButtonClick(View view){
        switch (view.getId()){
            case R.id.btnFastForward:
                break;
            case R.id.btnNext:
                mPlaylist.nextSong();
                mAudioService.nextSong();
                load();
                break;
            case R.id.btnPause:
                btnPlay.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.GONE);
                mAudioService.pauseSong();
                break;
            case R.id.btnPlay:
                btnPlay.setVisibility(View.GONE);
                btnPause.setVisibility(View.VISIBLE);
                mAudioService.playSong();
                break;
            case R.id.btnPrevious:
                mPlaylist.previousSong();
                mAudioService.previousSong();
                load();
                break;
            case R.id.btnRewind:
                break;
        }
    }

    private ServiceConnection audioServiceConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AudioService.AudioServiceBinder binder = (AudioService.AudioServiceBinder)service;

            mAudioService = binder.getService();

            mAudioService.addPlayList(mPlaylist);

            isPlaying = true;
            Log.d(Constants.TAG,"Service connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isPlaying = false;
        }
    };
}
