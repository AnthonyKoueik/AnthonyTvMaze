package com.koa.tvmaze.data.remote

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by ANTHONY KOUEIK on 7/16/2019.
 * Email: anthony.koueik@gmail.com
 */
class RequestInterceptor : Interceptor {

    companion object {
        private const val API_KEY = "2b2bb6fa089d4cc68eedd9ab00cca470"
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("apiKey", API_KEY)
            .build()

        val request = originalRequest.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}