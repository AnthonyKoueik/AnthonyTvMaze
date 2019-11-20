package com.koa.tvmaze

import android.app.Activity
import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.facebook.drawee.backends.pipeline.Fresco
import com.koa.tvmaze.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by ANTHONY KOUEIK on 7/15/2019.
 * Email: anthony.koueik@gmail.com
 */
class MyApplication : MultiDexApplication(), HasActivityInjector {

    companion object {
        lateinit var instance: MyApplication
            private set
    }

    @Inject
    lateinit var activityDispatchingInjector: DispatchingAndroidInjector<Activity>


    override fun onCreate() {
        super.onCreate()
        //DI Dagger Builder
        DaggerAppComponent.builder().application(this).build().inject(this)
        // My App instance
        instance = this

        // Initialize Fresco
        Fresco.initialize(this)

        // Initialize Debugger
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = activityDispatchingInjector
}