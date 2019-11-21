package com.koa.tvmaze

import android.app.Activity
import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.facebook.common.logging.FLog
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.listener.RequestListener
import com.facebook.imagepipeline.listener.RequestLoggingListener
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

        val requestListeners: MutableSet<RequestListener> = HashSet()
        requestListeners.add(RequestLoggingListener())
        val config = ImagePipelineConfig.newBuilder(this) // other setters
            .setRequestListeners(requestListeners)
            .build()
        Fresco.initialize(this, config)
        FLog.setMinimumLoggingLevel(FLog.VERBOSE)

        // Initialize Debugger
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> =
        activityDispatchingInjector
}