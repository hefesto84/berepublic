package com.berepublic.app.controller;

import com.berepublic.app.listener.ITunesListener;
import com.berepublic.app.model.Song;
import com.berepublic.app.net.ITunesWS;

/**
 * Created by dani on 9/5/16.
 */
public class ITunesController {

    private static ITunesController instance;

    private ITunesController(){

    }

    public static ITunesController getInstance(){
        if(instance==null){
            instance = new ITunesController();
        }
        return instance;
    }

    public void fetchSongList(String criteria, ITunesListener listener){
        ITunesWS.getInstance().fetchSongList(criteria,listener);
    }

}
