package com.koa.tvmaze.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.koa.tvmaze.data.entity.typeconverter.ExternalsTypeConverter
import com.koa.tvmaze.data.entity.typeconverter.ImageTypeConverter
import com.koa.tvmaze.data.entity.typeconverter.LinksXTypeConverter
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Show(

    @ColumnInfo(name = "show_table_id")
    val id: Int?,

    @ColumnInfo(name = "show_table_links")
    @TypeConverters(LinksXTypeConverter::class)
    val _links: LinksX,


    @ColumnInfo(name = "show_table_externals")
    @TypeConverters(ExternalsTypeConverter::class)
    val externals: Externals,


    @ColumnInfo(name = "show_table_image")
    @TypeConverters(ImageTypeConverter::class)
    val image: Image?,

    @ColumnInfo(name = "show_table_language")
    val language: String?,

    @ColumnInfo(name = "show_table_name")
    val name: String?,

    @ColumnInfo(name = "show_table_officialSite")
    val officialSite: String?,

    @ColumnInfo(name = "show_table_premiered")
    val premiered: String?,

    @ColumnInfo(name = "show_table_runtime")
    val runtime: Int?,

    @ColumnInfo(name = "show_table_status")
    val status: String?,

    @ColumnInfo(name = "show_table_summary")
    val summary: String?,

    @ColumnInfo(name = "show_table_type")
    val type: String?,

    @ColumnInfo(name = "show_table_updated")
    val updated: Int?,

    @ColumnInfo(name = "show_table_url")
    val url: String?,

    @ColumnInfo(name = "show_table_weight")
    val weight: Int?
): Parcelable