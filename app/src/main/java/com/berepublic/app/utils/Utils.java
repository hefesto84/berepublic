package com.berepublic.app.utils;

import com.berepublic.app.model.Song;

import java.util.List;

/**
 * Created by dani on 10/5/16.
 */
public class Utils {

    public static final int ORDER_BY_PRICE      = 0;
    public static final int ORDER_BY_GENRE      = 1;
    public static final int ORDER_BY_DURATION   = 2;

    public static List<Song> orderSongList(List<Song> songs, int orderBy){
        return songs;
    }

}
