package com.ac03.covid.model.server

import com.google.gson.annotations.SerializedName

data class CountryItem(
    @SerializedName("Country")
    val country: String,
    @SerializedName("ISO2")
    val iso2: String,
    @SerializedName("Slug")
    val slug: String
)
