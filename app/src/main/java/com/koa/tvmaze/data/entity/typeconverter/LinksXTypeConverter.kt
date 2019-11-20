package com.koa.tvmaze.data.entity.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.koa.tvmaze.data.entity.LinksX

/**
 * Created by ANTHONY KOUEIK on 11/20/2019.
 * Email: anthony.koueik@gmail.com
 */
class LinksXTypeConverter {

    @TypeConverter
    fun convertStringToSourceModel(data: String?): LinksX? {
        data?.let {
            return Gson().fromJson(it, LinksX::class.java)
        }
        return  null
    }

    @TypeConverter
    fun convertSourceModelToString(data: LinksX?): String? {
        return Gson().toJson(data)
    }
}