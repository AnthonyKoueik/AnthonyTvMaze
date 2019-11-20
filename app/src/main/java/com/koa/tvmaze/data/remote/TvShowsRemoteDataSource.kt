package com.koa.tvmaze.data.remote

import com.koa.tvmaze.data.TvShowsDataSource
import com.koa.tvmaze.data.entity.TvShows
import io.reactivex.Flowable
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ANTHONY KOUEIK on 7/16/2019.
 * Email: anthony.koueik@gmail.com
 *
 * Here I get the source from the remote
 */
@Singleton
class TvShowsRemoteDataSource @Inject constructor(private val apiService: ApiService) : TvShowsDataSource {

    override fun getTvShows(country: String, date : String): Flowable<List<TvShows>> {
        return apiService.getTvShows(country, date).map {
            if(it.size != null){
                Timber.i("TvShows - Success")
                it
            }else{
                throw HttpException(Response.success(it))
            }
        }
    }

    override fun saveTvShows(articles: List<TvShows>){}

    override fun refreshTvShows() {
    }
}