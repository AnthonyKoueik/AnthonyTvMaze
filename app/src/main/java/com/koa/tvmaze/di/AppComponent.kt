package com.koa.tvmaze.di

import com.koa.tvmaze.MyApplication
import com.koa.tvmaze.di.module.*
import com.koa.tvmaze.di.module.ActivityBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by ANTHONY KOUEIK on 7/15/2019.
 * Email: anthony.koueik@gmail.com
 */
@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, AppModule::class, NetworkModule::class,
        RepositoryModule::class, ActivityBindingModule::class, ViewModelModule::class,
        DatabaseModule::class]
)
interface AppComponent : AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MyApplication): Builder

        fun build(): AppComponent
    }
}