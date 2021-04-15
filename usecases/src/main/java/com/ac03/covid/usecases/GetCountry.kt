package com.ac03.covid.usecases

import com.ac03.covid.data.repository.CovidRepository
import com.ac03.covid.domain.Country
import com.ac03.covid.domain.SummaryData

class GetCountry(private val covidRepository: CovidRepository) {
    suspend fun invoke() : List<Country> = covidRepository.findCountries()
}