package com.example.myweather

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myweather.databinding.ActivitySelectCityBinding
import com.example.myweather.db.AppDatabase
import com.google.gson.Gson
import java.io.InputStreamReader
import kotlin.concurrent.thread

class SelectCityActivity : AppCompatActivity() {
    lateinit var binding: ActivitySelectCityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectCityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        thread {
            InputStreamReader(assets.open("areaCode")).use {
                val city = Gson().fromJson(it, CityList::class.java)
                runOnUiThread{
                    binding.cityListView.adapter = CityAdapter(city.city){root ->
                        val data = root.findViewById<TextView>(R.id.name).let {
                            CityData(it.id.toString(), it.text.toString())
                        }
                        saveCityData(data)
                        Log.d("CD KIM", "city data: $data")
                    }
                    binding.cityListView.layoutManager = LinearLayoutManager(this)
                }
            }
        }
    }
    fun saveCityData(data: CityData) {
        thread {
            AppDatabase.getInstance(this).cityDao().insertCityData(data)
        }
    }
}