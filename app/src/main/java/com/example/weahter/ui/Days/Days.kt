package com.example.weahter.ui.Days

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weahter.Adapter.DaysAdapter
import com.example.weahter.R
import com.example.weahter.data.Forecast
import com.example.weahter.data.Forecastday
import com.example.weahter.data.postdata
import com.example.weahter.data.weatherData
import com.example.weahter.databinding.FragmentDaysBinding
import com.example.weahter.databinding.FragmentTodayBinding
import com.example.weahter.ui.Today.TodayViewmodel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.ThreadLocalRandom.current

class Days : Fragment() {



    private val WeatherViewmodel by viewModel<TodayViewmodel>()
    private var _binding: FragmentDaysBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        WeatherViewmodel.postWeather(
            data = postdata(
                q = "Tashkent",
                days = 10,
                key = ""
            )
        )
        val rv = binding.recyclerview
        val layoutManager = LinearLayoutManager(context)
        rv.layoutManager = layoutManager
        WeatherViewmodel.weatherdata.observe(viewLifecycleOwner) { data ->
            val result:Forecast= data.forecast
            rv.adapter = DaysAdapter(requireContext(),result)

            Log.e("TAG", "onBindViewHolder: $result", )
        }
    }
    fun setData(forecastdays: Forecastday) {

    }


}