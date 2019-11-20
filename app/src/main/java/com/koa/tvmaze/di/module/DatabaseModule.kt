package com.koa.tvmaze.di.module

import androidx.room.Room
import com.koa.tvmaze.MyApplication
import com.koa.tvmaze.data.local.room.AppDatabase
import com.koa.tvmaze.data.local.room.TvShowsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by ANTHONY KOUEIK on 7/18/2019.
 * Email: anthony.koueik@gmail.com
 */
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: MyApplication): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "tvmaze1.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideTvShowsDao(database: AppDatabase): TvShowsDao = database.tvShowsDao()
}