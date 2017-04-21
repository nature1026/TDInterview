package com.td.td.retro;

import com.td.td.models.DuckDuckGo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Kay on 2017-04-19.
 */

public interface ApiService {

    @GET("?q=apple&format=json&pretty=1")
    Call<DuckDuckGo> getMyJSON();

    /*@GET
    Call<DuckDuckGo> getMovieDetails(
            @Url String url,
            @Query("q") String keyword,
            @Query("format") String query,
            @Query("movie_id") String movie_id,
            @Query("mp4") String mp4,
            @Query("photos") String photos);*/

}
