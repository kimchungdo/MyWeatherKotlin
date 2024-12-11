package com.example.myweather

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.myweather.data.WeekList
import com.google.gson.Gson
import java.io.BufferedInputStream
import java.net.URL

class CurrentWeatherWorker(context: Context, val param: WorkerParameters): Worker(context, param) {
    override fun doWork(): Result {
        val url = "https://api.openweathermap.org/data/2.5/weather?appid=83639cd84260050d123bb33c199f5b3f&id="
        val id = param.inputData.getString(SELECT_ID)
        val connect = URL(url + id).openConnection()
        BufferedInputStream(connect.getInputStream()).use {
            val data = Gson().fromJson(it.reader(), WeekList::class.java)
            Log.d("CD KIM", "data : $data")
        }
        return Result.success()
    }
}