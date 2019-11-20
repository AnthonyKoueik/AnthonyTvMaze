package com.koa.tvmaze.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Country(
    val code: String,
    val name: String,
    val timezone: String
): Parcelable