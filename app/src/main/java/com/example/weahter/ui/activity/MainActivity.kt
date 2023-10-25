package com.example.weahter.ui.activity

import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.weahter.Adapter.FragmentpageAdapter
import com.example.weahter.model.postdata
import com.example.weahter.model.weatherData
import com.example.weahter.databinding.ActivityMainBinding
import com.example.weahter.ui.Today.TodayViewmodel
import com.google.android.material.tabs.TabLayout
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: FragmentpageAdapter
    private val MainViewmodel by viewModel<TodayViewmodel>()

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tabLayout = binding.tablayout
        viewPager2 = binding.viewpager2
        adapter = FragmentpageAdapter(supportFragmentManager, lifecycle)
        viewPager2.adapter = adapter
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager2.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
        MainViewmodel.postWeather(
            data = postdata(
                q = "Tashkent",
                days = 10,
                key = ""
            )
        )
        MainViewmodel.weatherdata.observe(this) { data ->
            val result: weatherData = data
            current(result)
        }

    }

    fun current(weatherData: weatherData) {
        val current = weatherData.current
        val location = weatherData.location

        binding.region.text = "${location.country},${location.region}"
        binding.TodayGradus.text = "${current.temp_c.toInt()}°C"
        Glide.with(this).load("https:${current.condition.icon}").centerCrop()
            .into(binding.currentimage);
        binding.condinationtext.text = current.condition.text
        binding.daygradus.text = "Day ${weatherData.forecast.forecastday[0].day.maxtemp_c.toInt()}°C"
        binding.nightgradus.text ="Night ${weatherData.forecast.forecastday[0].day.mintemp_c.toInt()}°C"
        binding.date.text=getDate(weatherData.forecast.forecastday[0].date)

    }


    fun getDate(date: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val outputFormat = SimpleDateFormat("d MMMM yyyy", Locale.ENGLISH)
         val parsedDate = inputFormat.parse(date)
        return outputFormat.format(parsedDate)
    }

}