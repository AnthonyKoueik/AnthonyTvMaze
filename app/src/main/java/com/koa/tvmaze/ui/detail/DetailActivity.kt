package com.koa.tvmaze.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.koa.tvmaze.base.BaseActivity
import com.koa.tvmaze.data.Status
import com.koa.tvmaze.data.entity.Show
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_scrolling.*

/**
 * Created by ANTHONY KOUEIK on 7/17/2019.
 * Email: anthony.koueik@gmail.com
 *
 * Detailed view for the Article
 */

class DetailActivity : BaseActivity<DetailActivityViewModel>() {

    override fun layoutRes(): Int = com.koa.tvmaze.R.layout.activity_detail

    override fun getViewModel(): Class<DetailActivityViewModel> = DetailActivityViewModel::class.java

    companion object {

        const val EXTRA_SHOW = "show-extra"
        const val EXTRA_SUM = "show-summary"
        /* Passing the whole Model in the Intent
        * Will try to add a local Db later if i have time*/
        fun newIntent(context: Context, show: Show,  summary: String?): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_SHOW, show)
            intent.putExtra(EXTRA_SUM, summary)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        /* reading the model from the Intent*/
        viewModel.show = intent.getParcelableExtra(EXTRA_SHOW)
        viewModel.mainSummary = intent.getStringExtra(EXTRA_SUM)

        viewModel.liveData.observe(this, Observer { show ->
            when (show.status) {

                Status.SUCCESS -> {
                    show.data.let {

                        val url = it?.image?.original //1
                        Glide.with(this)  //2
                            .load(url) //3
                            .centerCrop() //4
                            .into(header_image_view) //8

                        it?.name?.let { title ->
                            tv_title_header.visibility = View.VISIBLE
                            tv_title_header.text = title
                        }

                        it?.status?.let { status ->
                            tv_author.visibility = View.VISIBLE
                            tv_author.text = status
                        }

                        it?.rating?.let { rating ->
                            tv_source.visibility = View.VISIBLE
                            tv_source.text = "Rating " + rating.average
                        }

                        viewModel.mainSummary?.let { description ->
                            tv_description.visibility = View.VISIBLE
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                tv_description.text =
                                    Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT)
                            } else {
                                tv_description.text = Html.fromHtml(description)
                            }
                        }

                        it?.premiered?.let { premiered ->
                            tv_time.visibility = View.VISIBLE
                            tv_time.text = "Premiered " + premiered/*DateUtils.getRelativeTimeSpanString(getDate(publishedAt,
                                    "yyyy-MM-dd'T'HH:mm:ss").time, System.currentTimeMillis(),
                                    DateUtils.HOUR_IN_MILLIS)*/
                        }

                        it?.summary?.let { summary ->
                            tv_content.visibility = View.VISIBLE
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                tv_content.text =
                                    Html.fromHtml(summary, Html.FROM_HTML_MODE_COMPACT)
                            } else {
                                tv_content.text = Html.fromHtml(summary)
                            }
                        }
                    }
                }
                else -> {

                }
            }
        })

        /*calling the time from the ViewModel Via Live Data*/
        viewModel.getShow()

    }
}
