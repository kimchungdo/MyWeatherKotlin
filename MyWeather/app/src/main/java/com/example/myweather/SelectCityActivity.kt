package com.example.myweather

import android.content.Intent
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

const val SELECT_ID = "select_id"
class SelectCityActivity : AppCompatActivity() {
    lateinit var binding: ActivitySelectCityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectCityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        thread {
            InputStreamReader(assets.open("areaCode")).use {input->
                val city = Gson().fromJson(input, CityList::class.java)
                runOnUiThread{
                    binding.cityListView.adapter = CityAdapter(city.city){root ->
                        val data = root.findViewById<TextView>(R.id.name).let {
                            CityData(it.tag.toString(), it.text.toString())
                        }
                        saveCityData(data)
                        setResult(RESULT_OK, Intent().putExtra(SELECT_ID, data.id))
                        Log.d("CD KIM", "city data: $data")
                        finish()
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