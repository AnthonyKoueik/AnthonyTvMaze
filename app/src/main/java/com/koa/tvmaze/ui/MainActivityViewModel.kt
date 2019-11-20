package com.koa.tvmaze.ui

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.koa.tvmaze.data.DataResource
import com.koa.tvmaze.data.entity.TvShows
import com.koa.tvmaze.data.repository.TvShowsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by ANTHONY KOUEIK on 7/17/2019.
 * Email: anthony.koueik@gmail.com
 *
 * My Main Activty View Model
 */
class MainActivityViewModel @Inject constructor(@VisibleForTesting val repository: TvShowsRepository) : ViewModel() {

    private var disposable: Disposable? = null
    val liveData = MutableLiveData<DataResource<List<TvShows>>>()


    fun loadTopNews(country: String, date: String, isRefresh: Boolean) {
        if (isRefresh) repository.refreshTvShows()

        disposable = repository.getTvShows(country, date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { liveData.setValue(DataResource.loading(null)) }
            .subscribe(
                { result -> liveData.setValue(DataResource.success(result)) },
                { throwable ->
                    liveData.setValue(
                        DataResource.error(
                            repository.getCustomErrorMessage(throwable), null
                        )
                    )
                }
            )
    }

    override fun onCleared() {
        disposable?.let {
            if (!it.isDisposed) it.dispose()
        }
    }

}