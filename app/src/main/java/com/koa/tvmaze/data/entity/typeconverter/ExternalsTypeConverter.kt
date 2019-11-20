package com.koa.tvmaze.data.entity.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.koa.tvmaze.data.entity.Externals

/**
 * Created by ANTHONY KOUEIK on 11/20/2019.
 * Email: anthony.koueik@gmail.com
 */
class ExternalsTypeConverter {

    @TypeConverter
    fun convertStringToSourceModel(data: String?): Externals? {
        data?.let {
            return Gson().fromJson(it, Externals::class.java)
        }
        return  null
    }

    @TypeConverter
    fun convertSourceModelToString(data: Externals?): String? {
        return Gson().toJson(data)
    }
}