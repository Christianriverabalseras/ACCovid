package com.ac03.covid.data.repository

import com.ac03.covid.data.source.LocalDataSource
import com.ac03.covid.data.source.RemoteDataSource
import com.ac03.covid.domain.Country
import com.ac03.covid.domain.SummaryData

class CovidRepository(private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource) {
    suspend fun getSummaryData(): SummaryData {
        if (localDataSource.isEmpty()) {
            val summaryData = remoteDataSource.getSummaryData()
            localDataSource.saveSummaryData(summaryData)
            localDataSource.saveCountries(summaryData.countries)
        }
        return localDataSource.getSummaryData()
    }

    // TODO: findCountries() to be implemented
}
