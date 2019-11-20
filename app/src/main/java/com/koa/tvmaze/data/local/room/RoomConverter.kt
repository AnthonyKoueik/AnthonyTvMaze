package com.koa.tvmaze.data.local.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import javax.xml.transform.Source

/**
 * Created by ANTHONY KOUEIK on 7/18/2019.
 * Email: anthony.koueik@gmail.com
 *
 * converting Source Model to string
 */
class RoomConverter {

    @TypeConverter
    fun convertStringToSourceModel(data: String?): Source? {
        data?.let {
            return Gson().fromJson(it, Source::class.java)
        }
        return  null
    }

    @TypeConverter
    fun convertSourceModelToString(data: Source?): String? {
        return Gson().toJson(data)
    }
}