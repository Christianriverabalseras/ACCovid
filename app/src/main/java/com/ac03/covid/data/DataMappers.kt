package com.ac03.covid.data

import com.ac03.covid.domain.Global
import com.ac03.covid.data.database.SummaryData as RoomSummaryData
import com.ac03.covid.domain.SummaryData as DomainSummaryData
import com.ac03.covid.data.database.Country as RoomCountry
import com.ac03.covid.domain.Country as DomainCountry
import com.ac03.covid.data.server.SummaryData as ServerSummaryData
import com.ac03.covid.data.server.Country as ServerCountry

fun DomainSummaryData.toRoomSummaryData(): RoomSummaryData = RoomSummaryData(
    id = 0,
    countries = countries.map(DomainCountry::toRoomCountry),
    date = date,
    totalConfirmed = global.totalConfirmed,
    totalDeaths = global.totalDeaths,
    totalRecovered = global.totalRecovered
)

fun DomainCountry.toRoomCountry(): RoomCountry = RoomCountry(
    id = 0,
    country = country,
    countryCode = countryCode,
    date = date,
    totalConfirmed = totalConfirmed,
    totalDeaths =  totalDeaths,
    totalRecovered = totalRecovered
)

fun RoomSummaryData.toDomainSummaryData(): DomainSummaryData = DomainSummaryData(
    countries = countries.map(RoomCountry::toDomainCountry),
    date = date,
    global = Global(
        totalConfirmed = totalConfirmed,
        totalDeaths = totalDeaths,
        totalRecovered = totalRecovered
    )
)

fun RoomCountry.toDomainCountry(): DomainCountry = DomainCountry(
    country = country,
    countryCode = countryCode,
    date = date,
    totalConfirmed = totalConfirmed,
    totalDeaths =  totalDeaths,
    totalRecovered = totalRecovered
)

fun ServerSummaryData.toDomainSummaryData(): DomainSummaryData = DomainSummaryData(
    countries = countries.map(ServerCountry::toDomainCountry),
    date = date,
    global = Global(
        totalConfirmed = global.totalConfirmed,
        totalDeaths = global.totalDeaths,
        totalRecovered = global.totalRecovered
    )
)

fun ServerCountry.toDomainCountry(): DomainCountry = DomainCountry(
    country = country,
    countryCode = countryCode,
    date = date,
    totalConfirmed = totalConfirmed,
    totalDeaths =  totalDeaths,
    totalRecovered = totalRecovered
)

