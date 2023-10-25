package com.example.weahter.model

data class weatherData(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)