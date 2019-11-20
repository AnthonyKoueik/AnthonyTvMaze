package com.koa.tvmaze.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.koa.tvmaze.data.entity.TvShows
import io.reactivex.Flowable
import io.reactivex.Maybe

/**
 * Created by ANTHONY KOUEIK on 11/20/2019.
 * Email: anthony.koueik@gmail.com
 */
@Dao
interface TvShowsDao {

    @Suppress("unused")
    @Query("SELECT * FROM tv_shows ORDER BY tvshowairstamp DESC")
    fun getAllTvShows(): Maybe<List<TvShows>>

    @Suppress("unused")
    @Query("SELECT * FROM tv_shows WHERE tv_shows_id = :tvShowsId")
    fun getTvShowsById(tvShowsId: Int): Flowable<TvShows>

    @Suppress("unused")
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(show: TvShows)

    @Suppress("unused")
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShowsList(showList: List<TvShows>)

    @Suppress("unused")
    @Query("DELETE FROM tv_shows")
    fun deleteAll()
}