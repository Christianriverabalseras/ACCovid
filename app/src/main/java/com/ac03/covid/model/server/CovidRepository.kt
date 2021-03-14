package com.ac03.covid.model.server

import com.ac03.covid.CovidApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.ac03.covid.model.database.Country as DbCountry
import com.ac03.covid.model.server.Country as ServerCountry

class CovidRepository(application: CovidApplication) {

    private val db = application.db

    suspend fun findCountries(): SummaryData = withContext(Dispatchers.IO) {

        val countries = CovidServiceFactory.service.getSummary()

        with(db.countryDao()) {
            if (countryCount() <= 0) {
                insertCountries(countries.countries.map(ServerCountry::convertToDbCountry))
            }

            countries
        }
    }

    suspend fun findById(id: Int): DbCountry = withContext(Dispatchers.IO) {
        db.countryDao().findById(id)
    }

    suspend fun update(country: DbCountry) = withContext(Dispatchers.IO) {
        db.countryDao().updateCountry(country)
    }
}

private fun ServerCountry.convertToDbCountry() = DbCountry(
    0,
    country,
    countryCode,
    date,
    newConfirmed,
    newDeaths,
    newRecovered,
    slug,
    totalConfirmed,
    totalDeaths,
    totalRecovered
)
