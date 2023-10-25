package com.example.weahter.repository

import com.example.weahter.model.postdata
import com.example.weahter.model.weatherData

interface Repository{
    suspend fun PostWeather(data:postdata):weatherData
}