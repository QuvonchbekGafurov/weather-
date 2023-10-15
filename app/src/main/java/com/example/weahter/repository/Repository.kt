package com.example.weahter.repository

import com.example.weahter.data.postdata
import com.example.weahter.data.weatherData
import retrofit2.http.POST

interface Repository{
    suspend fun PostWeather(data:postdata):weatherData
}