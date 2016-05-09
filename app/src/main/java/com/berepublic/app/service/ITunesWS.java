package com.berepublic.app.service;


import com.berepublic.app.listener.ITunesListener;
import com.berepublic.app.model.Song;
import com.berepublic.app.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dani on 9/5/16.
 */
public class ITunesWS {

    private static ITunesService service;
    private static ITunesWS instance = null;

    private ITunesWS(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.WS_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ITunesService.class);

    }

    public static ITunesWS getInstance(){
        if(instance == null){
            instance = new ITunesWS();
        }
        return instance;
    }

    public void fetchSongList(String criteria, final ITunesListener listener){
        service.fetchSongList(criteria).enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                listener.OnITunesSongListReceived(response.body());
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                listener.OnITunesError(t.getMessage());
            }
        });
    }
}
