package com.koa.tvmaze.data.remote

import com.koa.tvmaze.data.entity.TvShows
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by ANTHONY KOUEIK on 7/16/2019.
 * Email: anthony.koueik@gmail.com
 */
interface ApiService {

    companion object {
        const val BASE_PATH = ""
    }

    @GET("$BASE_PATH/schedule")
    fun getTvShows(@Query("country") country: String, @Query("date") date: String)
            : Flowable<List<TvShows>>
}