package com.ac03.covid.model.server

import com.ac03.covid.CovidApplication
import com.ac03.covid.model.database.Country as DbCountry
//import com.ac03.covid.model.server.Country as ServerCountry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CovidRepository(application: CovidApplication) {

/*private val db = application.db

suend fun findCountries(): List<DbCountry> = withContext(Dispatchers.IO) {

    with(db.countryDao()) {
        if (countryCount() <= 0) {

            val countries = CovidServiceFactory.service
                .getCountries()

            insertCountries(countries.map())
        }
    }
}*/
}

/*private fun ServerCountry.convertToDbCountry() = DbCountry(

)*/
