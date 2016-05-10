package com.berepublic.app.controller;

/**
 * Created by dani on 10/5/16.
 */
public class MediaPlayerController {

    private static MediaPlayerController instance = null;

    private MediaPlayerController(){

    }

    public static MediaPlayerController getInstance(){
        if(instance == null){
            instance = new MediaPlayerController();
        }
        return instance;
    }
}
