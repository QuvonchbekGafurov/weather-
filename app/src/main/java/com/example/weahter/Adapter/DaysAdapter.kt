package com.example.weahter.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weahter.R
import com.example.weahter.model.Forecast

class DaysAdapter(private val context:Context,private val forecast:Forecast) : RecyclerView.Adapter<DaysAdapter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val day: TextView = itemView.findViewById(R.id.day)
        val weather: TextView = itemView.findViewById(R.id.weather)
        val daygradus: TextView = itemView.findViewById(R.id.daygradus)
        val nightgradus:TextView=itemView.findViewById(R.id.nightgradus)
        val img2:ImageView=itemView.findViewById(R.id.rvimg)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.days_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.e("TAG", "onBindViewHolder: $forecast", )
        val OneDay = forecast.forecastday[position]
        val date=OneDay.date
        val weather=OneDay.day.condition.text
        val daygr=OneDay.day.maxtemp_c
        val nightgradus=OneDay.day.mintemp_c
        val img=OneDay.day.condition.icon
        holder.day.text = date
        holder.weather.text = weather
        holder.daygradus.text = "${daygr.toInt()}°C"
        holder.nightgradus.text = "${nightgradus.toInt()}°C"
        Glide.with(context).load("https:${img}").centerCrop().into(holder.img2);
    }

    override fun getItemCount(): Int {
        return forecast.forecastday.size
    }

}