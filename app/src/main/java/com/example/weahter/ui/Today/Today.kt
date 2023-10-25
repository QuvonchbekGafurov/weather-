package com.example.weahter.ui.Today

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.weahter.R
import com.example.weahter.data.Current
import com.example.weahter.data.Location
import com.example.weahter.data.postdata
import com.example.weahter.data.weatherData
import com.example.weahter.databinding.FragmentTodayBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.eazegraph.lib.charts.ValueLineChart
import org.eazegraph.lib.models.BarModel
import org.eazegraph.lib.models.ValueLinePoint
import org.eazegraph.lib.models.ValueLineSeries
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.ThreadLocalRandom.current

class Today : Fragment() {

    private val MainViewmodel by viewModel<TodayViewmodel>()
    private var _binding: FragmentTodayBinding? = null
    private val binding get() = _binding!!
    var chart: ValueLineChart? = null
    val TAG = "TAG"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentTodayBinding.inflate(inflater, container, false)
        MainViewmodel.postWeather(
            data = postdata(
                q = "Tashkent",
                days = 10,
                key = ""
            )
        )

        fused = LocationServices.getFusedLocationProviderClient(requireContext())
        MainViewmodel.weatherdata.observe(viewLifecycleOwner) { data ->
            val result: weatherData = data
            set(result)
        }

        return binding.root
    }
fun set(result: weatherData){
    setChart(result)
    setHourlyforecast(result)
    binding.sunrisetext.text= result.forecast.forecastday[0].astro.sunrise
    binding.sunsettext.text= result.forecast.forecastday[0].astro.sunset

}
    fun setChart(result: weatherData) {
        var weekend=result.forecast
        val series = ValueLineSeries()
        series.color = -0x3e81f7
        var point :ValueLinePoint
        var week:ArrayList<String>
        week=haftaKuni(weekend.forecastday[0].date)
        Log.e(TAG, "setter: $week", )
        chart = binding.chart
        var minGradus = Float.MAX_VALUE
        var maxGradus = Float.MIN_VALUE
        for (i in 0..6) {
            var gradus = weekend.forecastday[i].day.avgtemp_c.toFloat()
            if (gradus < minGradus) {
                minGradus = gradus
            }
            if (gradus > maxGradus) {
                maxGradus = gradus
            }
            point= ValueLinePoint("${week[i]}", gradus*1.0f)
            series.addPoint(point)
        }

        chart!!.addSeries(series)
        chart!!.startAnimation()
    }
    fun setHourlyforecast(result: weatherData){
        val data=result.forecast.forecastday[0].hour
        val now=result.location.localtime.substring(11,13).toInt()
        for (i in 0..10 )
    }

    fun dateToText(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM", Locale.getDefault())
        val date = inputFormat.parse(dateString)
        val result = outputFormat.format(date)
        Log.e(TAG, "dateToText: $result")
        return result
    }


    private lateinit var fused: FusedLocationProviderClient


    private fun getlocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 100
            )
        }
        var latitude: String
        var longitude: String

        var lacation = fused.lastLocation.addOnSuccessListener {
            if (it != null) {
                latitude = it.latitude.toString()
                longitude = it.longitude.toString()
                MainViewmodel.postWeather(
                    data = postdata(
                        q = "$latitude,$longitude",
                        days = 10,
                        key = ""
                    )
                )
            }
        }



    }
    fun haftaKuni(berilganSana: String):ArrayList<String>{
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(berilganSana)
        val haftaKunlari = arrayOf("Dushanba", "Seshanba", "Chorshanba", "Payshanba", "Juma", "Shanba", "Yakshanba")
        val cal = date?.let {
            val calendar = java.util.Calendar.getInstance()
            calendar.time = it
            calendar
        }
        val haftaKuniIndeksi = cal?.get(java.util.Calendar.DAY_OF_WEEK) ?: 0
        var week= haftaKunlari[haftaKuniIndeksi - 2]
        var Weeks=ArrayList<String>(7)
        var i=haftaKuniIndeksi - 2
        if (i<0) {
            i += 7
        }
            Log.e(TAG, "haftaKuni: $i",)
            for (j in 0..6) {
                if (i == 7) {
                    i = 0
                }
                Weeks.add(haftaKunlari[i])
                i++
            }

        return Weeks
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}







