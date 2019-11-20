package com.koa.tvmaze.utils

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ANTHONY KOUEIK on 7/17/2019.
 * Email: anthony.koueik@gmail.com
 */


fun getDate(date: String?, dateFormat: String): Date {
    val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
    return formatter.parse(date)
}


@Suppress("unused")
fun getRelativeTime(time: Long): String {
    return DateUtils.getRelativeTimeSpanString(
        time * 1000,
        System.currentTimeMillis(),
        DateUtils.DAY_IN_MILLIS,
        DateUtils.FORMAT_ABBREV_ALL
    ).toString()
}