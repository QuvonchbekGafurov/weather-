package com.example.weahter.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.weahter.data.postdata
import com.example.weahter.data.weatherData
import com.example.weahter.databinding.FragmentMainBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class Main : Fragment() {

    private val MainViewmodel by viewModel<MainViewmodel>()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    val TAG = "TAG"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        // Observe changes in weather data using the fragment's view lifecycle
        viewLifecycleOwner.lifecycleScope.launch {
            MainViewmodel.weatherdata.observe(viewLifecycleOwner) { data ->
                // Handle the updated weather data here
                Log.e(TAG, "onCreateView: $data")
            }
        }

        // Post weather data
        MainViewmodel.PostWeather(data = postdata(q = "Tashkent", days = 2, key = ""))

        return binding.root
    }
}


