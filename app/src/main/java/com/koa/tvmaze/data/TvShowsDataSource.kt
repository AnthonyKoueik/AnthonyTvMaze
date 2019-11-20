package com.koa.tvmaze.data

import com.koa.tvmaze.data.entity.TvShows
import io.reactivex.Flowable

/**
 * Created by ANTHONY KOUEIK on 7/16/2019.
 * Email: anthony.koueik@gmail.com
 */
interface TvShowsDataSource {

    fun getTvShows(country: String, date : String): Flowable<List<TvShows>>

    fun saveTvShows(tvShowsList: List<TvShows>)

    fun refreshTvShows()
}