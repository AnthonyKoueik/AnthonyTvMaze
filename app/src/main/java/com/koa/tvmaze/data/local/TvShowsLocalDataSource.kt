package com.koa.tvmaze.data.local

import com.koa.tvmaze.data.TvShowsDataSource
import com.koa.tvmaze.data.entity.TvShows
import com.koa.tvmaze.data.local.room.TvShowsDao
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ANTHONY KOUEIK on 7/18/2019.
 * Email: anthony.koueik@gmail.com
 *
 * Here I get the source from the database
 */
@Singleton
class TvShowsLocalDataSource @Inject constructor(
    private val tvShowsDao: TvShowsDao
) : TvShowsDataSource {


    override fun getTvShows(country: String, date: String): Flowable<List<TvShows>> {
        return tvShowsDao.getAllTvShows().toFlowable()
    }

    override fun saveTvShows(tvShows: List<TvShows>) {
        tvShowsDao.apply {
            deleteAll()
            insertTvShowsList(tvShows)
        }
    }

    override fun refreshTvShows() {
    }
}