package com.koa.tvmaze.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.koa.tvmaze.R
import com.koa.tvmaze.base.BaseActivity
import com.koa.tvmaze.data.DataResource
import com.koa.tvmaze.data.Status
import com.koa.tvmaze.data.entity.TvShows
import com.koa.tvmaze.ui.detail.DetailActivity
import com.koa.tvmaze.utils.SpacesItemDecoration
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber


class MainActivity : BaseActivity<MainActivityViewModel>() {

    override fun layoutRes(): Int = R.layout.activity_main

    override fun getViewModel(): Class<MainActivityViewModel> = MainActivityViewModel::class.java

    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        newsAdapter = NewsAdapter(baseContext, ArrayList(0))

        val cols = resources.getInteger(R.integer.grid_columns)
        recyclerView.apply {
            layoutManager = GridLayoutManager(context, cols)
            (layoutManager as GridLayoutManager).spanSizeLookup =
                object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (position == 0) cols else 1
                    }
                }
            val spacing = resources.getDimensionPixelSize(R.dimen.spacing)
            addItemDecoration(SpacesItemDecoration(spacing))
            adapter = newsAdapter
            onItemClick()
        }


        viewModel.liveData.observe(this, Observer { apiResponse ->

            bindResponse(apiResponse)
            Timber.i(apiResponse.message)
        })

        viewModel.loadTopNews("US", "2019-10-10", false)

        sw_refresher.setOnRefreshListener {
            viewModel.loadTopNews("US", "2019-10-10", true)
        }
    }

    private fun onItemClick() {

        newsAdapter.onItemClick = { item ->

            if (item is TvShows) {
                if (item.summary != null)
                    startActivity(DetailActivity.newIntent(baseContext, item.show, item.summary))
                else
                    startActivity(DetailActivity.newIntent(baseContext, item.show, "Season " + item.season))
            }
        }
    }

    private fun bindResponse(apiResponse: DataResource<List<TvShows>>) {


        when (apiResponse.status) {

            Status.LOADING -> {
                Timber.d("LOADING :: ")
                showLoader()
            }

            Status.SUCCESS -> {
                Timber.d("SUCCESS :: %s", apiResponse.data)
                hideLoader()
                apiResponse.data?.let {
                    setupList(apiResponse.data)
                }
            }

            Status.ERROR -> {
                Timber.e("ERROR :: %s", apiResponse.message)
                Toasty.error(
                    applicationContext, apiResponse.message.toString(),
                    Toast.LENGTH_LONG, true
                ).show()
                hideLoader()
            }
        }
    }

    private fun showLoader() {
        sw_refresher.isRefreshing = true
        tv_no_data.visibility = View.GONE
    }

    private fun hideLoader() {
        sw_refresher.isRefreshing = false
    }

    private fun showList() {
        recyclerView.visibility = View.VISIBLE
        tv_no_data.visibility = View.GONE
    }

    private fun hideList() {
        recyclerView.visibility = View.GONE
        tv_no_data.visibility = View.VISIBLE
    }

    private fun setupList(list: List<Any>) {

        list.let {
            newsAdapter.setData(it)
            when (it.size) {
                0 -> {
                    hideList()
                }
                else -> {
                    showList()
                }
            }
        }
    }
}
