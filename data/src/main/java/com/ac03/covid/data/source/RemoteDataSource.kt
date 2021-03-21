package com.ac03.covid.data.source

import com.ac03.covid.domain.SummaryData

interface RemoteDataSource {
    suspend fun getSummaryData(): SummaryData
}
