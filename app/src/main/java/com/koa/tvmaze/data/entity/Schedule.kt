package com.koa.tvmaze.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Schedule(
    val days: List<String>,
    val time: String
) : Parcelable