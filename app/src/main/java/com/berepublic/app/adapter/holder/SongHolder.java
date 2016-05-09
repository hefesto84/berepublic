package com.berepublic.app.adapter.holder;

import android.view.View;

import com.berepublic.app.model.Song;

/**
 * Created by dani on 9/5/16.
 */
public class SongHolder {

    private View view;
    private Song song;

    public SongHolder(View view, Song song){
        this.view = view;
        this.song = song;
    }

}
