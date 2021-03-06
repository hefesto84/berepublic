package com.berepublic.app.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.berepublic.app.R;
import com.berepublic.app.adapter.SongAdapter;
import com.berepublic.app.adapter.SuggestionsAdapter;
import com.berepublic.app.controller.ITunesController;
import com.berepublic.app.filter.SongFilter;
import com.berepublic.app.listener.ITunesListener;
import com.berepublic.app.model.Playlist;
import com.berepublic.app.model.Song;
import com.berepublic.app.utils.Constants;
import com.berepublic.app.widget.SuggestionsTextView;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener, ITunesListener{

    private SongAdapter mAdapter;
    private Playlist mPlaylist;
    private List<Song> mListSongs = new ArrayList<Song>();
    private int mLastFilter = SongFilter.FILTER_DISABLED;

    @Bind(R.id.lstSongList)         ListView mList;
    @Bind(R.id.txtSearch)           SuggestionsTextView txtSearch;
    @Bind(R.id.btnOrderByDuration)  Button btnOrderByDuration;
    @Bind(R.id.btnOrderByPrice)     Button btnOrderByPrice;
    @Bind(R.id.btnOrderByGenre)     Button btnOrderByGenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mAdapter = new SongAdapter(this,mListSongs,R.layout.item_list_song);
        mList.setAdapter(mAdapter);
        mList.setOnItemClickListener(this);

        txtSearch.setThreshold(3);

        final SuggestionsAdapter adapter = new SuggestionsAdapter(this);

        txtSearch.setAdapter(adapter);
        txtSearch.setLoadingIndicator(null);
        txtSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Song song = (Song)adapterView.getItemAtPosition(position);
                String criteria = song.artistName + " " + song.trackName;
                txtSearch.setText(criteria);
                hideKeyboard();
                ITunesController.getInstance().fetchSongList(criteria,MainActivity.this);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this,PlayerActivity.class);
        mPlaylist.currentSong = i;
        intent.putExtra("playlist",Parcels.wrap(mPlaylist));
        startActivity(intent);
    }

    @Override
    public void OnITunesSongListReceived(List<Song> songs) {
        mPlaylist = new Playlist(songs,0);
        mAdapter.clear();
        mAdapter.addAll(mPlaylist.songs);
        mAdapter.notifyDataSetChanged();
        mLastFilter = SongFilter.FILTER_DISABLED;
    }

    @Override
    public void OnITunesError(String error) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.btnOrderByDuration, R.id.btnOrderByGenre, R.id.btnOrderByPrice, R.id.btnSearch})
    public void orderBy(View view){

        if(mPlaylist==null || mPlaylist.songs.size() == 0 || mPlaylist.songs == null){
            return;
        }

        switch (view.getId()){
            case R.id.btnOrderByDuration:

                mLastFilter =(((mLastFilter == SongFilter.FILTER_BY_DURATION_ASC) || (mLastFilter == SongFilter.FILTER_DISABLED))?SongFilter.FILTER_BY_DURATION_DES:SongFilter.FILTER_BY_DURATION_ASC);

                if(mLastFilter == SongFilter.FILTER_BY_DURATION_ASC) {
                    Collections.sort(mPlaylist.songs, Song.Duration);
                }else {
                    Collections.reverse(mListSongs);
                }

                break;
            case R.id.btnOrderByGenre:

                mLastFilter =(((mLastFilter == SongFilter.FILTER_BY_GENRE_ASC) || (mLastFilter == SongFilter.FILTER_DISABLED))?SongFilter.FILTER_BY_GENRE_DES:SongFilter.FILTER_BY_GENRE_ASC);

                if(mLastFilter == SongFilter.FILTER_BY_GENRE_ASC) {
                    Collections.sort(mPlaylist.songs, Song.Genre);
                }else {
                    Collections.reverse(mPlaylist.songs);
                }

                break;
            case R.id.btnOrderByPrice:

                mLastFilter =(((mLastFilter == SongFilter.FILTER_BY_PRICE_ASC) || (mLastFilter == SongFilter.FILTER_DISABLED))?SongFilter.FILTER_BY_PRICE_DES:SongFilter.FILTER_BY_PRICE_ASC);

                if(mLastFilter == SongFilter.FILTER_BY_PRICE_ASC) {
                    Collections.sort(mListSongs, Song.Price);
                }else {
                    Collections.reverse(mPlaylist.songs);
                }

                break;

            case R.id.btnSearch:
                if(txtSearch.getText().length()!=0 && txtSearch.getText() != null) {
                    ITunesController.getInstance().fetchSongList(txtSearch.getText().toString(), MainActivity.this);
                }
                break;
        }

        mAdapter.clear();
        mAdapter.addAll(mPlaylist.songs);
        mAdapter.notifyDataSetChanged();

    }

    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
    }

}
