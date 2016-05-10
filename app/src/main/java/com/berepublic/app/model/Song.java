package com.berepublic.app.model;

import org.parceler.Parcel;

import java.util.Date;

/**
 * Created by dani on 9/5/16.
 */

@Parcel
public class Song {

    public int artistId;
    public long collectionId;
    public long trackId;
    public String artistName;
    public String collectionName;
    public String trackName;
    public String collectionCensoredName;
    public String trackCensoredName;
    public String artistViewUrl;
    public String collectionViewUrl;
    public String trackViewUrl;
    public String previewUrl;
    public String artworkUrl30;
    public String artworkUrl60;
    public String artworkUrl100;
    public float collectionPrice;
    public float trackPrice;
    public Date releaseDate;
    public String collectionExplicitness;
    public String trackExplicitness;
    public int discCount;
    public int discNumber;
    public int trackCount;
    public int trackNumber;
    public int trackTimeMilis;
    public String country;
    public String currency;
    public String primaryGenreName;
    boolean isStremeable;

    public Song() {

    }

}
