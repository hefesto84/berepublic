package com.berepublic.app.utils;

import com.berepublic.app.model.Song;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dani on 10/5/16.
 */
public class Utils {

    public static String toSimpleDate(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

}
