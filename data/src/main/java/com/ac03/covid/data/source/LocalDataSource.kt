package com.ac03.covid.data.source

import com.ac03.covid.domain.Country
import com.ac03.covid.domain.SummaryData

interface LocalDataSource {
    suspend fun isCountriesEmpty() : Boolean
    suspend fun isSummaryDataEmpty() : Boolean
    suspend fun saveSummaryData(summaryData: SummaryData)
    suspend fun getSummaryData(): SummaryData
    suspend fun saveCountries(countries: List<Country>)
    suspend fun getCountries(): List<Country>
}