package com.koa.tvmaze.di.module

import com.koa.tvmaze.di.ActivityScoped
import com.koa.tvmaze.ui.MainActivity
import com.koa.tvmaze.ui.detail.DetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by ANTHONY KOUEIK on 7/17/2019.
 * Email: anthony.koueik@gmail.com
 */
@Module
internal abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): MainActivity

    @ActivityScoped
    @ContributesAndroidInjector
    internal abstract fun bindDetailActivity(): DetailActivity
}