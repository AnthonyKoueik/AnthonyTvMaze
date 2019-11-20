package com.koa.tvmaze.di.module

import com.koa.tvmaze.data.TvShowsDataSource
import com.koa.tvmaze.data.local.TvShowsLocalDataSource
import com.koa.tvmaze.data.remote.TvShowsRemoteDataSource
import com.koa.tvmaze.di.qualifier.LocalData
import com.koa.tvmaze.di.qualifier.RemoteData
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Created by ANTHONY KOUEIK on 7/16/2019.
 * Email: anthony.koueik@gmail.com
 */
@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    @RemoteData
    abstract fun bindNewsRemoteDataSource(newsRemoteDataSource: TvShowsRemoteDataSource): TvShowsDataSource

    @Singleton
    @Binds
    @LocalData
    abstract fun bindNewsLocalDataSource(newsLocalDataSource: TvShowsLocalDataSource): TvShowsDataSource
}