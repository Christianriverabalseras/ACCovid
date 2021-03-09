package com.ac03.covid.model.server

import retrofit2.http.GET

interface CovidService {
    @GET("/summary")
    suspend fun getSummary(): SummaryData

    @GET("/countries")
    suspend fun getCountries(): Countries
}
