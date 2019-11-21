package com.koa.tvmaze.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.koa.tvmaze.data.entity.Show
import com.koa.tvmaze.data.entity.TvShows
import com.koa.tvmaze.data.entity.typeconverter.*

/**
 * Created by ANTHONY KOUEIK on 7/18/2019.
 * Email: anthony.koueik@gmail.com
 */
@Database(entities = [TvShows::class], version = 1)
@TypeConverters(
    ListConverters::class, ExternalsTypeConverter::class,
    LinksXTypeConverter::class, ImageTypeConverter::class, RatingTypeConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tvShowsDao(): TvShowsDao
}