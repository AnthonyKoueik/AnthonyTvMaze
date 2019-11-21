package com.koa.tvmaze.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.koa.tvmaze.data.DataResource
import com.koa.tvmaze.data.entity.Show
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by ANTHONY KOUEIK on 7/17/2019.
 * Email: anthony.koueik@gmail.com
 */
class DetailActivityViewModel @Inject constructor() : ViewModel() {

    private var disposable: Disposable? = null
    val liveData = MutableLiveData<DataResource<Show>>()

    lateinit var show: Show
    lateinit var mainSummary: String

    fun getShow(){
        liveData.value = DataResource.success(show)
    }

    override fun onCleared() {
        disposable?.let {
            if (!it.isDisposed) it.dispose()
        }
    }
}