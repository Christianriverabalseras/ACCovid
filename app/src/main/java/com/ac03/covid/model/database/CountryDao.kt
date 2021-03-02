package com.ac03.covid.model.database

import androidx.room.*

@Dao
interface CountryDao {

    @Query("SELECT * FROM Country")
    fun getAll(): List<Country>

    @Query("SELECT * FROM Country WHERE id = :id")
    fun findById(id: Int): List<Country>

    @Query("SELECT COUNT(id) FROM Country")
    fun countryCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCountries(country: List<Country>)

    @Update
    fun updateCountry(country: Country)
}
