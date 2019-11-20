package com.koa.tvmaze.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LinksX(
    val nextepisode: Nextepisode,
    val previousepisode: Previousepisode,
    val self: SelfX
): Parcelable