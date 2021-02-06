package com.ac03.covid.entites


import com.google.gson.annotations.SerializedName

data class SummaryData(
    @SerializedName("Countries")
    val countries: List<Country>,
    @SerializedName("Date")
    val date: String,
    @SerializedName("Global")
    val global: Global
)