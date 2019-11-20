package com.koa.tvmaze.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.format.DateUtils
import android.view.View
import androidx.lifecycle.Observer
import com.koa.tvmaze.base.BaseActivity
import com.koa.tvmaze.data.Status
import com.koa.tvmaze.data.entity.Show
import com.koa.tvmaze.utils.getDate
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

        /* Passing the whole Model in the Intent
        * Will try to add a local Db later if i have time*/
        fun newIntent(context: Context, show: Show): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_SHOW, show)
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

        viewModel.liveData.observe(this, Observer { show ->
            when (show.status) {

                Status.SUCCESS -> {
                    show.data.let {

                        header_image_view.run { setImageURI(it?.image?.original) }

                        it?.name?.let { title ->
                            tv_title_header.visibility = View.VISIBLE
                            tv_title_header.text = title
                        }

                        it?.status?.let { author ->
                            tv_author.visibility = View.VISIBLE
                            tv_author.text = author
                        }

                        it?.officialSite?.let { source ->
                            tv_source.visibility = View.VISIBLE
                            tv_source.text = source
                        }

                        it?.summary?.let { description ->
                            tv_description.visibility = View.VISIBLE
                            tv_description.text = description
                        }

                        it?.updated?.let { publishedAt ->
                            tv_time.visibility = View.VISIBLE
                            tv_time.text = publishedAt.toString()/*DateUtils.getRelativeTimeSpanString(getDate(publishedAt,
                                    "yyyy-MM-dd'T'HH:mm:ss").time, System.currentTimeMillis(),
                                    DateUtils.HOUR_IN_MILLIS)*/
                        }

                        it?.summary?.let { summary ->
                            tv_content.visibility = View.VISIBLE
                            tv_content.text = summary
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
