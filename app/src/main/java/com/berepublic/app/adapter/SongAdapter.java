package com.berepublic.app.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.berepublic.app.R;
import com.berepublic.app.model.Song;
import com.berepublic.app.utils.Utils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dani on 9/5/16.
 */
public class SongAdapter extends ArrayAdapter<Song> {

    private List<Song> mSongs;
    private Context mContext;
    private int mLayout;
    private LayoutInflater mInflater;

    public SongAdapter(Context context, List<Song> songs, int layout){
        super(context,layout,songs);
        this.mContext = context;
        this.mLayout = layout;
        this.mSongs = songs;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount(){
        return mSongs.size();
    }

    @Override
    public Song getItem(int position){
        return mSongs.get(position);
    }

    @Override
    public long getItemId(int position){
        return mSongs.get(position).hashCode();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder holder;

        if (view == null) {
            view = mInflater.inflate(mLayout, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.albumName.setText(mSongs.get(position).collectionCensoredName);
        holder.artistName.setText(mSongs.get(position).artistName);
        holder.songName.setText(mSongs.get(position).trackName);
        holder.album.setImageURI(Uri.parse(mSongs.get(position).artworkUrl60));
        holder.genreName.setText(mSongs.get(position).primaryGenreName);

        String date = Utils.toSimpleDate(mSongs.get(position).releaseDate);
        holder.releaseDate.setText(date);

        holder.price.setText(mSongs.get(position).trackPrice + mSongs.get(position).currency);
        return view;
    }

    static class ViewHolder{

        @Bind(R.id.txtSongName) TextView songName;
        @Bind(R.id.txtArtistName) TextView artistName;
        @Bind(R.id.txtAlbumName) TextView albumName;
        @Bind(R.id.imgAlbum) SimpleDraweeView album;
        @Bind(R.id.txtGenre) TextView genreName;
        @Bind(R.id.txtReleaseDate) TextView releaseDate;
        @Bind(R.id.txtPrice) TextView price;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
