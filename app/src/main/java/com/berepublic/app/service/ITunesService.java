package com.berepublic.app.service;

import com.berepublic.app.model.ITunesResponse;
import com.berepublic.app.model.Song;
import com.berepublic.app.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by dani on 9/5/16.
 */
public interface ITunesService {

    @GET(Constants.WS_ITUNES)
    Call<ITunesResponse> fetchSongList(
            @Query(Constants.WS_FIELD_SEARCH) String criteria
    );
}
