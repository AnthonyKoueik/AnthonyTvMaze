package com.koa.tvmaze.data.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tv_shows")
data class TvShows(

    @PrimaryKey
    @ColumnInfo(name = "tv_shows_id")
    val id: Long?,

    @ColumnInfo(name = "tvshowairdate")
    val airdate: String?,

    @ColumnInfo(name = "tvshowairtime")
    val airtime: String?,

    @ColumnInfo(name = "tvshowairstamp")
    val airstamp: String?,

    @ColumnInfo(name = "tvshowname")
    val name: String?,

    @ColumnInfo(name = "tvshownumber")
    val number: Int?,

    @ColumnInfo(name = "tvshowruntime")
    val runtime: Int?,

    @ColumnInfo(name = "tvshowseason")
    val season: Int?,

    @ColumnInfo(name = "tvshowsummary")
    val summary: String?,

    @ColumnInfo(name = "tvshowurl")
    val url: String?,

    @Embedded
    val show: Show
)