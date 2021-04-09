package com.ac03.covid.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    companion object {
        private val gson = Gson()

        @TypeConverter
        @JvmStatic
        fun fromJson(json: String): List<Country> {
            val type = object : TypeToken<List<Country>>() {}.type
            return gson.fromJson(json, type)
        }

        @TypeConverter
        @JvmStatic
        fun toJson(countries: List<Country>): String {
            val type = object: TypeToken<List<Country>>() {}.type
            return gson.toJson(countries, type)
        }
    }
}