package com.ac03.covid.domain

data class SummaryData(
    val countries: List<Country>,
    val date: String,
    val global: Global
)