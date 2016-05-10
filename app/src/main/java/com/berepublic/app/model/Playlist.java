package com.berepublic.app.model;

import android.util.Log;

import com.berepublic.app.utils.Constants;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.List;

/**
 * Created by Dani on 11/05/2016.
 */

@Parcel
public class Playlist {

    public List<Song> songs;
    public int currentSong;

    @ParcelConstructor
    public Playlist(List<Song> songs, int currentSong){
        this.songs = songs;
        this.currentSong = currentSong;
    }

    public void nextSong(){
        if(currentSong < songs.size()) {
            currentSong++;
        }else{
            currentSong = 0;
        }
    }

    public void previousSong(){
        if(currentSong > 0){
            currentSong--;
        }else{
            currentSong = songs.size()-1;
        }
    }
}
