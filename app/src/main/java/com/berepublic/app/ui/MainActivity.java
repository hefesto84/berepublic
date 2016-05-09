package com.berepublic.app.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.berepublic.app.R;
import com.berepublic.app.adapter.SongAdapter;
import com.berepublic.app.model.Song;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener{

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
    }


    private List<Song> testSongList(){
        List<Song> songs = new ArrayList<Song>();
        for(int i = 0; i<10; i++){
            songs.add(new Song());
        }
        return songs;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
