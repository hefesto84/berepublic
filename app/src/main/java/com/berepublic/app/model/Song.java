package com.berepublic.app.model;

import org.parceler.Parcel;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by dani on 9/5/16.
 */

@Parcel
public class Song implements Comparable<Song>{

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

    @Override
    public int compareTo(Song song) {
        if(song.trackPrice < this.trackPrice){
            return -1;
        }
        if(song.trackPrice > this.trackPrice){
            return 1;
        }
        return 0;
    }

    public static Comparator<Song> Price = new Comparator<Song>() {
        @Override
        public int compare(Song song, Song t1) {
            if(song.trackPrice < t1.trackPrice){
                return -1;
            }
            if(song.trackPrice > t1.trackPrice){
                return 1;
            }
            return 0;
        }
    };

    public static Comparator<Song> Duration = new Comparator<Song>() {
        @Override
        public int compare(Song song, Song t1) {
            if(song.trackTimeMilis < t1.trackTimeMilis){
                return -1;
            }
            if(song.trackTimeMilis > t1.trackTimeMilis){
                return 1;
            }
            return 0;
        }
    };

    public static Comparator<Song> Genre = new Comparator<Song>() {
        @Override
        public int compare(Song song, Song t1) {
            return song.primaryGenreName.toLowerCase().compareTo(t1.primaryGenreName.toLowerCase());
        }
    };
}
