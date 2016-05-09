package com.berepublic.app.listener;

import com.berepublic.app.model.Song;

import java.util.List;

/**
 * Created by dani on 9/5/16.
 */
public interface ITunesListener {
    void OnITunesSongListReceived(List<Song> songs);
    void OnITunesError(String error);
}
