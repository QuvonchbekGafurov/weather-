package com.example.weahter.remote

import com.example.weahter.model.weatherData
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

        @POST("v1/forecast.json")
        suspend fun getWeatherForecast(
            @Query("key") apiKey: String,
            @Query("q") location: String,
            @Query("days") days: Int,
        ): weatherData

}