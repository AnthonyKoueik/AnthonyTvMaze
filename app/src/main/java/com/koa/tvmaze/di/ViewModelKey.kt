package com.koa.tvmaze.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Created by ANTHONY KOUEIK on 7/15/2019.
 * Email: anthony.koueik@gmail.com
 */
@Target(allowedTargets = [AnnotationTarget.FUNCTION])
@Retention(value = AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)