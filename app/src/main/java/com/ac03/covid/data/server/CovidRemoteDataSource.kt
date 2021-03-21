package com.ac03.covid.data.server

import com.ac03.covid.data.source.RemoteDataSource
import com.ac03.covid.data.toDomainSummaryData
import com.ac03.covid.domain.SummaryData

class CovidRemoteDataSource : RemoteDataSource{
    override suspend fun getSummaryData(): SummaryData {
        return CovidServiceFactory.service.getSummary().toDomainSummaryData()
    }
}