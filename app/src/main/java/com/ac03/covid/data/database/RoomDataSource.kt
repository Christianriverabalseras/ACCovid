package com.ac03.covid.data.database

import com.ac03.covid.data.source.LocalDataSource
import com.ac03.covid.data.toDomainCountry
import com.ac03.covid.data.toDomainSummaryData
import com.ac03.covid.data.toRoomCountry
import com.ac03.covid.data.toRoomSummaryData
import com.ac03.covid.domain.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.ac03.covid.domain.SummaryData as DomainSummaryData

class RoomDataSource @Inject constructor(private val dao: CountryDao) : LocalDataSource {

    override suspend fun isCountriesEmpty(): Boolean =
        withContext(Dispatchers.IO) { dao.countryCount() >= 0 }

    override suspend fun isSummaryDataEmpty(): Boolean =
        withContext(Dispatchers.IO) { dao.summaryDataCount() >= 0 }

    override suspend fun saveSummaryData(summaryData: DomainSummaryData) {
        withContext(Dispatchers.IO) {
            dao.insertSummaryData(summaryData.toRoomSummaryData())
        }
    }

    override suspend fun getSummaryData(): DomainSummaryData =
        withContext(Dispatchers.IO) {
            val countries = dao.getCountries().map { it.toDomainCountry() }
            dao.getSummaryData().toDomainSummaryData(countries)
        }

    override suspend fun saveCountries(countries: List<Country>) {
        withContext(Dispatchers.IO) {
            dao.insertCountries(countries.map {
                it.toRoomCountry()
            })
        }
    }

    override suspend fun getCountries(): List<Country> =
        withContext(Dispatchers.IO) {
            dao.getCountries().map { it.toDomainCountry() }
        }
}