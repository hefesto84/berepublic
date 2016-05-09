package com.berepublic.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.berepublic.app.adapter.holder.SongHolder;
import com.berepublic.app.model.Song;

import java.util.List;

/**
 * Created by dani on 9/5/16.
 */
public class SongAdapter extends ArrayAdapter<Song> {

    private List<Song> songs;
    private Context context;
    private int layout;

    public SongAdapter(Context context, List<Song> songs, int layout){
        super(context,layout,songs);
        this.context = context;
        this.layout = layout;
        this.songs = songs;
    }

    @Override
    public int getCount(){
        return songs.size();
    }

    @Override
    public Song getItem(int position){
        return songs.get(position);
    }

    @Override
    public long getItemId(int position){
        return songs.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SongHolder holder = null;
        View item = convertView;

        if (item == null || !( item.getTag() instanceof SongHolder)) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            item = inflater.inflate(layout, parent, false);
            holder = new SongHolder(item,getItem(position));
            item.setTag(holder);
        }else{
            holder = (SongHolder) item.getTag();
        }


        return item;

    }
}
