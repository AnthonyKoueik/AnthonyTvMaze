package com.koa.tvmaze.data.entity.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.koa.tvmaze.data.entity.Image

/**
 * Created by ANTHONY KOUEIK on 11/20/2019.
 * Email: anthony.koueik@gmail.com
 */
class ImageTypeConverter {

    @TypeConverter
    fun convertStringToSourceModel(data: String?): Image? {
        data?.let {
            return Gson().fromJson(it, Image::class.java)
        }
        return  null
    }

    @TypeConverter
    fun convertSourceModelToString(data: Image?): String? {
        return Gson().toJson(data)
    }
}