package com.koa.tvmaze.data.entity.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

/**
 * Created by ANTHONY KOUEIK on 11/20/2019.
 * Email: anthony.koueik@gmail.com
 */
class ListConverters {
    @TypeConverter
    fun fromString(value: String?): ArrayList<String?>? {
        val listType = object :
            TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson<ArrayList<String?>>(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<String?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}