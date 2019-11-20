package com.koa.tvmaze.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    val medium: String,
    val original: String
) : Parcelable