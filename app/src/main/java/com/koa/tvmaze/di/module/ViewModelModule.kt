package com.koa.tvmaze.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.koa.tvmaze.base.ViewModelFactory
import com.koa.tvmaze.di.ViewModelKey
import com.koa.tvmaze.ui.MainActivityViewModel
import com.koa.tvmaze.ui.detail.DetailActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by ANTHONY KOUEIK on 7/17/2019.
 * Email: anthony.koueik@gmail.com
 */
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(viewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailActivityViewModel::class)
    abstract fun bindDetailActivityViewModel(viewModel: DetailActivityViewModel): ViewModel

}