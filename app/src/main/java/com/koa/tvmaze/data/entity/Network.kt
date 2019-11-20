package com.koa.tvmaze.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Network(
    val country: Country,
    val id: Int,
    val name: String
) : Parcelable