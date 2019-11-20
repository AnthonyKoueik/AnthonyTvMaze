package com.koa.tvmaze.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by ANTHONY KOUEIK on 7/17/2019.
 * Email: anthony.koueik@gmail.com
 *
 * base view holder for binding view
 */
abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}