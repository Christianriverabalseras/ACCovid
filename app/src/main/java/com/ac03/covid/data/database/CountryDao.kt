package com.ac03.covid.data.database

import androidx.room.*

@Dao
interface CountryDao {

    @Query("SELECT * FROM Country")
    fun getAll(): List<Country>

    @Query("SELECT * FROM Country WHERE id = :id")
    fun findById(id: Int): Country

    @Query("SELECT COUNT(id) FROM Country")
    fun countryCount(): Int

    @Query("SELECT * FROM Country")
    fun getCountries(): List<Country>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCountries(country: List<Country>)

    @Query("SELECT * FROM SummaryData")
    fun getSummaryData(): SummaryData

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSummaryData(summaryData: SummaryData)

    @Update
    fun updateCountry(country: Country)
}
