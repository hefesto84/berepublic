package com.berepublic.app.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.berepublic.app.R;
import com.berepublic.app.adapter.SongAdapter;
import com.berepublic.app.controller.ITunesController;
import com.berepublic.app.listener.ITunesListener;
import com.berepublic.app.model.Song;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener, ITunesListener{

    private SongAdapter mAdapter;
    private List<Song> mListSongs;

    @Bind(R.id.lstSongList) ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mListSongs = testSongList();

        mAdapter = new SongAdapter(this,mListSongs,R.layout.item_list_song);
        mList.setAdapter(mAdapter);
        mList.setOnItemClickListener(this);

        ITunesController.getInstance().fetchSongList("Anaal Nathrakh",this);
    }


    private List<Song> testSongList(){
        List<Song> songs = new ArrayList<Song>();
        return songs;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this,PlayerActivity.class);
        intent.putExtra("song", Parcels.wrap(mAdapter.getItem(i)));
        startActivity(intent);
    }

    @Override
    public void OnITunesSongListReceived(List<Song> songs) {
        mAdapter.clear();
        mAdapter.addAll(songs);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnITunesError(String error) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
    }
}
