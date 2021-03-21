package com.ac03.covid.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SummaryData(
    val countries: List<Country>,
    @PrimaryKey
    val date: String,
    val totalConfirmed: Int,
    val totalDeaths: Int,
    val totalRecovered: Int
)
