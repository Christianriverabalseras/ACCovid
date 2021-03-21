package com.ac03.covid.data.database

import com.ac03.covid.data.source.LocalDataSource
import com.ac03.covid.data.toDomainSummaryData
import com.ac03.covid.data.toRoomSummaryData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.ac03.covid.domain.SummaryData as DomainSummaryData

class RoomDataSource(db: CovidDatabase) : LocalDataSource {

    private val dao = db.countryDao()

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { dao.countryCount() >= 0 }

    override suspend fun saveSummaryData(summaryData: DomainSummaryData) {
        withContext(Dispatchers.IO) { dao.insertSummaryData(summaryData.toRoomSummaryData()) }
    }

    override suspend fun getSummaryData(): DomainSummaryData =
        withContext(Dispatchers.IO) { dao.getSummaryData().toDomainSummaryData() }
}