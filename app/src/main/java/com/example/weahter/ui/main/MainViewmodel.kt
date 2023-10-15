package com.example.weahter.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weahter.data.postdata
import com.example.weahter.data.weatherData
import com.example.weahter.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewmodel(var repository: Repository):ViewModel() {
    private val _weatherdata = MutableLiveData<weatherData>()
    val weatherdata: LiveData<weatherData> = _weatherdata

    fun PostWeather(data:postdata) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result=repository.PostWeather(data=data)
                _weatherdata.postValue(result)
                Log.e("TAG", "PostWeather: $result", )
            }  catch (e: Exception) {
                Log.e("TAG", "PostWeather: ${e.message}", )
                e.message
            }
        }
    }
}