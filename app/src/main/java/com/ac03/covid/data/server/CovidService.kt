package com.ac03.covid.data.server

import retrofit2.http.GET

interface CovidService {
    @GET("/summary")
    suspend fun getSummary(): SummaryData
}
