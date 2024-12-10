package com.example.myweather

import androidx.room.Entity

data class CityList(var city:ArrayList<CityData>)
@Entity(tableName = "city_list") //add DB
data class CityData(var id: String, var name: String)
