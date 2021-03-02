package com.ac03.covid.model.server

import com.ac03.covid.entites.Countries
import retrofit2.http.GET

interface CovidService {
    @GET("/summary")
    suspend fun getSummary(): SummaryData

    @GET("/countries")
    suspend fun getCountries(): Countries
}
