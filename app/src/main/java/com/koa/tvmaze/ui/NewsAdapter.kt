package com.koa.tvmaze.ui

import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.koa.tvmaze.R
import com.koa.tvmaze.base.BaseViewHolder
import com.koa.tvmaze.data.entity.TvShows
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.row_header.*
import kotlinx.android.synthetic.main.row_show.*
import java.lang.IllegalArgumentException

/**
 * Created by ANTHONY KOUEIK on 7/17/2019.
 * Email: anthony.koueik@gmail.com
 */
class NewsAdapter(private val context: Context, dataList: List<Any>) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var adapterDataList: List<Any> = dataList
    var onItemClick: ((Any) -> Unit)? = null

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1

    }

    override fun getItemCount(): Int = adapterDataList.size

    override fun getItemViewType(position: Int): Int = when (position) {
        0 -> TYPE_HEADER
        else -> TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            TYPE_HEADER -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.row_header, parent, false)
                HeaderViewHolder(view)
            }
            TYPE_ITEM -> {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.row_show, parent, false)
                NewsViewHolder(view)
            }
            else -> throw IllegalArgumentException("BundlesAdapter Invalid View Type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val element = adapterDataList[position]
        when (holder) {
            is HeaderViewHolder -> holder.bind(element as TvShows)
            is NewsViewHolder -> holder.bind(element as TvShows)
            else -> throw IllegalArgumentException()
        }
    }

    fun setData(news: List<Any>) {

        this.adapterDataList = news
        notifyDataSetChanged()
    }

    /* My Header View in the List */
    inner class HeaderViewHolder(itemView: View) : BaseViewHolder<TvShows>(itemView)
        , LayoutContainer {

        override val containerView: View?
            get() = itemView

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(adapterDataList[adapterPosition])
            }
        }

        /*binding the data with the view*/
        override fun bind(item: TvShows) {

            row_iv_header.run { setImageURI(item.show.image?.medium) }
            row_tv_title_header.text = item.show.name
            row_tv_desc_header.text = " "
            item.summary?.let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    row_tv_desc_header.text =
                        Html.fromHtml(item.summary, Html.FROM_HTML_MODE_COMPACT)
                } else {
                    row_tv_desc_header.text = Html.fromHtml(item.summary)
                }
            }

            row_tv_time_header.text = "at " + item.airtime/*DateUtils.getRelativeTimeSpanString(getDate(item.publishedAt,
                    "yyyy-MM-dd'T'HH:mm:ss").time, System.currentTimeMillis(),
                    DateUtils.HOUR_IN_MILLIS)*/
        }
    }

    /* My Item View in the List */
    inner class NewsViewHolder(itemView: View) : BaseViewHolder<TvShows>(itemView)
        , LayoutContainer {

        override val containerView: View?
            get() = itemView

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(adapterDataList[adapterPosition])
            }
        }

        /*binding the data with the view*/
        override fun bind(item: TvShows) {

            row_iv_news.run { setImageURI(item.show.image?.medium) }
            row_tv_title.text = item.show.name
            row_tv_desc_news.text = " "
            item.summary?.let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    row_tv_desc_news.text = Html.fromHtml(item.summary, Html.FROM_HTML_MODE_COMPACT)
                } else {
                    row_tv_desc_news.text = Html.fromHtml(item.summary)
                }
            }

            row_tv_time.text = "at " + item.airtime/*DateUtils.getRelativeTimeSpanString(getDate(item.publishedAt,
                    "yyyy-MM-dd'T'HH:mm:ss").time, System.currentTimeMillis(),
                    DateUtils.HOUR_IN_MILLIS)*/
        }
    }
}