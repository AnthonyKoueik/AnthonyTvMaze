package com.koa.tvmaze.data.entity.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.koa.tvmaze.data.entity.LinksX
import com.koa.tvmaze.data.entity.Rating

/**
 * Created by ANTHONY KOUEIK on 11/21/2019.
 * Email: anthony.koueik@gmail.com
 */
class RatingTypeConverter {

    @TypeConverter
    fun convertStringToSourceModel(data: String?): Rating? {
        data?.let {
            return Gson().fromJson(it, Rating::class.java)
        }
        return  null
    }

    @TypeConverter
    fun convertSourceModelToString(data: Rating?): String? {
        return Gson().toJson(data)
    }
}