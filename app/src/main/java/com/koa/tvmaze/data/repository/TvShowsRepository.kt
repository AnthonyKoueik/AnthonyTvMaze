package com.koa.tvmaze.data.repository

import com.google.gson.stream.MalformedJsonException
import com.koa.tvmaze.MyApplication
import com.koa.tvmaze.R
import com.koa.tvmaze.data.TvShowsDataSource
import com.koa.tvmaze.data.entity.TvShows
import com.koa.tvmaze.di.qualifier.LocalData
import com.koa.tvmaze.di.qualifier.RemoteData
import io.reactivex.Flowable
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ANTHONY KOUEIK on 7/16/2019.
 * Email: anthony.koueik@gmail.com
 */
@Singleton
class TvShowsRepository @Inject constructor(
    @RemoteData private val remoteDataSource: TvShowsDataSource,
    @LocalData private val localDataSource: TvShowsDataSource
) : TvShowsDataSource {


    private var cachedNews = listOf<TvShows>()

    private var cacheNewsIsDirty = false

    override fun getTvShows(country: String, date: String): Flowable<List<TvShows>> {

        //return remoteDataSource.getTopNews(country)
        if (cachedNews.isNotEmpty() && !cacheNewsIsDirty) {
            return Flowable.just(cachedNews)
        }

        val remoteNews = getAndSaveRemoteTvShows(country, date)

        return if (cacheNewsIsDirty)
            remoteNews
        else {
            getAndCacheLocalTvShows(country, date)
            // val localNews =
            //Flowable.concat(localNews, remoteNews)
        }
    }

    override fun saveTvShows(tvShowsList: List<TvShows>) {
        localDataSource.saveTvShows(tvShowsList)
    }


    override fun refreshTvShows() {
        cacheNewsIsDirty = true
    }

    private fun getAndSaveRemoteTvShows(country: String, date: String): Flowable<List<TvShows>> {
        return remoteDataSource.getTvShows(country, date)
            .doOnNext { news ->
                localDataSource.saveTvShows(news)
                cachedNews = news
            }.doOnComplete {
                cacheNewsIsDirty = false
            }
    }

    private fun getAndCacheLocalTvShows(country: String, date: String): Flowable<List<TvShows>> {
        return localDataSource.getTvShows(country, date).doOnNext {
                news -> cachedNews = news
        }
    }


    fun getCustomErrorMessage(error: Throwable): String {

        return if (error is SocketTimeoutException) {
            MyApplication.instance.getString(R.string.requestTimeOutError)
        } else if (error is MalformedJsonException) {
            MyApplication.instance.getString(R.string.responseMalformedJson)
        } else if (error is IOException) {
            MyApplication.instance.getString(R.string.networkError)
        } else if (error is HttpException) {
            error.response().message()
        } else {
            MyApplication.instance.getString(R.string.unknownError)
        }
    }
}