package com.berepublic.app.service;

import com.berepublic.app.model.Song;
import com.berepublic.app.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

/**
 * Created by dani on 9/5/16.
 */
public interface ITunesService {

    @FormUrlEncoded
    @GET(Constants.WS_ITUNES)
    Call<List<Song>> fetchSongList(
            @Field(Constants.WS_FIELD_SEARCH) String criteria
    );
}
