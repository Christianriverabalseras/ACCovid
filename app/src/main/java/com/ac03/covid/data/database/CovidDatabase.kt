package com.ac03.covid.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [SummaryData::class, Country::class], version = 3)
@TypeConverters(Converters::class)
abstract class CovidDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDao
}
