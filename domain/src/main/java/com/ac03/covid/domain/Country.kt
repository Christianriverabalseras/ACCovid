package com.ac03.covid.domain

data class Country(
    val country: String,
    val countryCode: String,
    val date: String,
    val totalConfirmed: Int,
    val totalDeaths: Int,
    val totalRecovered: Int
)
