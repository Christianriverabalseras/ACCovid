package com.ac03.covid.usecases

import com.ac03.covid.data.repository.CovidRepository
import com.ac03.covid.domain.SummaryData

class GetSummaryData(private val covidRepository: CovidRepository) {
    suspend fun invoke() : SummaryData = covidRepository.getSummaryData()
}