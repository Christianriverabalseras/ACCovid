package com.ac03.covid.data.source

import com.ac03.covid.domain.SummaryData

interface LocalDataSource {
    suspend fun isEmpty() : Boolean
    suspend fun saveSummaryData(summaryData: SummaryData)
    suspend fun getSummaryData(): SummaryData
}