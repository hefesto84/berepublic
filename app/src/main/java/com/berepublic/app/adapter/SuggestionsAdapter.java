package com.berepublic.app.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.berepublic.app.R;
import com.berepublic.app.controller.ITunesController;
import com.berepublic.app.model.Song;
import com.berepublic.app.utils.Constants;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dani on 10/5/16.
 */
public class SuggestionsAdapter extends BaseAdapter implements Filterable {

    private Context mContext;
    private List<Song> mSongs = new ArrayList<Song>();
    private LayoutInflater mInflater;

    public SuggestionsAdapter(Context context){
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mSongs.size();
    }

    @Override
    public Song getItem(int i) {
        return mSongs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder holder;

        if (view == null) {
            view = mInflater.inflate(R.layout.item_list_song_suggestion, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.artistName.setText(mSongs.get(position).artistName);
        holder.songName.setText(mSongs.get(position).trackName);
        holder.album.setImageURI(Uri.parse(mSongs.get(position).artworkUrl60));

        return view;

    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    List<Song> songs = ITunesController.getInstance().fetchSuggestions(constraint.toString());
                    filterResults.values = songs;
                    filterResults.count = songs.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults data) {
                if (mSongs != null && data.count > 0) {
                    mSongs = (List<Song>) data.values;
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }};
        return filter;

    }

    static class ViewHolder{

        @Bind(R.id.txtSongName)     TextView songName;
        @Bind(R.id.txtArtistName)   TextView artistName;
        @Bind(R.id.imgAlbum)        SimpleDraweeView album;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
