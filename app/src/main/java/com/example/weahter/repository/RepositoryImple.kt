package com.example.weahter.repository
import com.example.weahter.data.postdata
import com.example.weahter.data.weatherData
import com.example.weahter.remote.ApiInterface

class RepositoryImple(val apiInterface: ApiInterface) : Repository {
    override suspend fun PostWeather(data: postdata): weatherData {
        val response = apiInterface.getWeatherForecast( "0811576534d34b33a5753306231310",data.q, data.days)
        return response
    }
}