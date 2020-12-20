package com.ac03.covid.entites


import com.google.gson.annotations.SerializedName

data class SummaryData(
    @SerializedName("Countries")
    val countries: List<Country>,
    @SerializedName("Date")
    val date: String,
    @SerializedName("Global")
    val global: Global
) {
    data class Country(
        @SerializedName("Country")
        val country: String,
        @SerializedName("TotalConfirmed")
        val totalConfirmed: Int,
        @SerializedName("TotalDeaths")
        val totalDeaths: Int,
        @SerializedName("TotalRecovered")
        val totalRecovered: Int
    )

    data class Global(
        @SerializedName("TotalConfirmed")
        val totalConfirmed: Int,
        @SerializedName("TotalDeaths")
        val totalDeaths: Int,
        @SerializedName("TotalRecovered")
        val totalRecovered: Int
    )
}