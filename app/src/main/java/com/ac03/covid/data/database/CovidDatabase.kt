package com.ac03.covid.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SummaryData::class, Country::class], version = 4, exportSchema = false)
abstract class CovidDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
}
