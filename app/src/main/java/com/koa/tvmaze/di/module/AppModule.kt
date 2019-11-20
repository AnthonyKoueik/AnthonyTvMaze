package com.koa.tvmaze.di.module

import android.app.Application
import android.content.Context
import com.koa.tvmaze.MyApplication
import dagger.Binds
import dagger.Module

/**
 * Created by ANTHONY KOUEIK on 7/15/2019.
 * Email: anthony.koueik@gmail.com
 */

@Module
abstract class AppModule() {

    @Binds
    abstract fun bindApplicationContext(application: MyApplication): Context

    @Binds
    abstract fun bindApplication(application: MyApplication): Application
}