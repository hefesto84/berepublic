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

import butterknife.ButterKnife;

/**
 * Created by dani on 10/5/16.
 */
public class SuggestionsAdapter extends BaseAdapter implements Filterable {

    private Context mContext;
    private List<Song> mSongs = new ArrayList<Song>();

    public SuggestionsAdapter(Context context){
        mContext = context;
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
    public View getView(int i, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_list_song_suggestion, parent, false);
        }

        ((TextView) convertView.findViewById(R.id.txtArtistName)).setText(getItem(i).artistName);
        ((TextView) convertView.findViewById(R.id.txtTrackName)).setText(getItem(i).trackName);
        ((SimpleDraweeView)convertView.findViewById(R.id.imgAlbum)).setImageURI(Uri.parse(getItem(i).artworkUrl30));

        return convertView;
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
}
