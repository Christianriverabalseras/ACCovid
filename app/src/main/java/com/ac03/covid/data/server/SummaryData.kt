package com.ac03.covid.data.server

import com.ac03.covid.entites.Global
import com.google.gson.annotations.SerializedName

data class SummaryData(
    @SerializedName("Countries")
    val countries: List<Country>,
    @SerializedName("Date")
    val date: String,
    @SerializedName("Global")
    val global: Global
)
