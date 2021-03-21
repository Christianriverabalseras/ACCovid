package com.ac03.covid.data.server

import com.ac03.covid.CovidApplication
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CovidServiceFactory {

    const val BASE_URL = "https://api.covid19api.com/"

    private val okHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient
            .Builder()
            .addInterceptor(this)
            .addInterceptor(FlipperOkhttpInterceptor(CovidApplication.get().flipperNetworkPlugin))
            .build()
    }

    val service: CovidService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .run {
            create(CovidService::class.java)
        }
}
