package com.ac03.covid.api

import com.ac03.covid.entites.SummaryData
import retrofit2.http.GET

interface CovidApiService {

    @GET("/summary")
    suspend fun getSummary() : SummaryData
}