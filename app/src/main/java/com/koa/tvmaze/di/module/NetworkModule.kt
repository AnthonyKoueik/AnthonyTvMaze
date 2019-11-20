package com.koa.tvmaze.di.module

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.koa.tvmaze.data.remote.ApiService
import com.koa.tvmaze.data.remote.RequestInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by ANTHONY KOUEIK on 7/16/2019.
 * Email: anthony.koueik@gmail.com
 *
 *  Creating the Retrofit Instance
 */
@Module
class NetworkModule {

    companion object {
        private const val NEWS_URL = "https://api.tvmaze.com/"
    }

    private fun buildOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .connectTimeout(10L, TimeUnit.SECONDS)
            .writeTimeout(10L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
            .addInterceptor(RequestInterceptor())
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = buildOkHttpClient()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(NEWS_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}