package com.berepublic.app.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;

import com.berepublic.app.model.Song;
import com.berepublic.app.utils.Constants;

import java.util.List;

/**
 * Created by Dani on 10/05/2016.
 */
public class AudioService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener{

    private MediaPlayer mPlayer;
    private List<Song> mSongs;
    private int mCurrentSong;
    private final IBinder mAudioServiceBinder = new AudioServiceBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        initialize();
    }

    public void initialize(){
        mCurrentSong = 0;
        mPlayer = new MediaPlayer();
        mPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPlayer.setOnPreparedListener(this);
        mPlayer.setOnCompletionListener(this);
        mPlayer.setOnErrorListener(this);
    }

    public void addPlayList(List<Song> songs){
        this.mSongs = songs;
    }

    public void playSong(){
        mPlayer.reset();

        try{
            mPlayer.setDataSource(mSongs.get(0).previewUrl);
        }catch(Exception e){
            Log.e(Constants.TAG,"Error playing song.");
        }

        mPlayer.prepareAsync();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mAudioServiceBinder;
    }

    @Override
    public boolean onUnbind(Intent intent){
        mPlayer.stop();
        mPlayer.release();
        return false;
    }

    public class AudioServiceBinder extends Binder {
        public AudioService getService() {
            return AudioService.this;
        }
    }
}
