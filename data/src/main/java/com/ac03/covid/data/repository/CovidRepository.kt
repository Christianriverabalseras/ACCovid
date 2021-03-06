package com.ac03.covid.data.repository

import com.ac03.covid.data.source.LocalDataSource
import com.ac03.covid.data.source.RemoteDataSource
import com.ac03.covid.domain.Country
import com.ac03.covid.domain.SummaryData

class CovidRepository(private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource) {
    suspend fun getSummaryData(): SummaryData {
        if (localDataSource.isSummaryDataEmpty()) {
            val summaryData = remoteDataSource.getSummaryData()
            localDataSource.saveSummaryData(summaryData)
            localDataSource.saveCountries(summaryData.countries)
        }
        return localDataSource.getSummaryData()
    }

    suspend fun findCountries(): List<Country> {
        if (localDataSource.isCountriesEmpty()) {
            val countries = remoteDataSource.getSummaryData()
            localDataSource.saveSummaryData(countries)
            localDataSource.saveCountries(countries.countries)
        }
        return localDataSource.getCountries()
    }
}
