package com.berepublic.app.net;


import android.util.Log;

import com.berepublic.app.listener.ITunesListener;
import com.berepublic.app.model.ITunesResponse;
import com.berepublic.app.model.Song;
import com.berepublic.app.service.ITunesService;
import com.berepublic.app.utils.Constants;

import java.util.ArrayList;
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
    private static final int MAX_SUGGESTIONS = 10;

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
        service.fetchSongList(criteria).enqueue(new Callback<ITunesResponse>() {
            @Override
            public void onResponse(Call<ITunesResponse> call, Response<ITunesResponse> response) {
                listener.OnITunesSongListReceived(response.body().getResults());
            }

            @Override
            public void onFailure(Call<ITunesResponse> call, Throwable t) {
                listener.OnITunesError(t.getMessage());
            }
        });
    }

    public List<Song> fetchSuggestions(String criteria){
        try{
            return service.fetchSuggestions(criteria,MAX_SUGGESTIONS).execute().body().getResults();
        }catch(Exception e) {
            Log.d(Constants.TAG,"Error retrieving suggestions.");
            return new ArrayList<Song>();
        }
    }
}
