package com.berepublic.app.adapter.holder;

import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.berepublic.app.R;
import com.berepublic.app.model.Song;
import com.facebook.drawee.view.SimpleDraweeView;

import org.w3c.dom.Text;

/**
 * Created by dani on 9/5/16.
 */
public class SongHolder {

    private View view;
    private Song song;
    private TextView songName;
    private TextView artistName;
    private TextView albumName;
    private SimpleDraweeView album;
    private TextView genreName;

    public SongHolder(View view, Song song){
        this.view = view;
        this.song = song;
        songName = (TextView)view.findViewById(R.id.txtSongName);
        artistName = (TextView)view.findViewById(R.id.txtArtistName);
        albumName = (TextView)view.findViewById(R.id.txtAlbumName);
        album = (SimpleDraweeView)view.findViewById(R.id.imgSong);
        genreName = (TextView)view.findViewById(R.id.txtGenre);
        fill();
    }

    private void fill(){
        songName.setText(song.getTrackName());
        artistName.setText(song.getArtistName());
        albumName.setText(song.getCollectionCensoredName());
        album.setImageURI(Uri.parse(song.getArtworkUrl60()));
        genreName.setText(song.getPrimaryGenreName());
    }

}
