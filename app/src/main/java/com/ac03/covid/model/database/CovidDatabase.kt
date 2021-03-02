package com.ac03.covid.model.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Country::class], version = 1)
abstract class CovidDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDao
}
