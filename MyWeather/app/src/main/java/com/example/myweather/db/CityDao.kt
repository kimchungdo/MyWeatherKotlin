package com.example.myweather.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myweather.CityData

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCityData(data: CityData)

    @Query("SELECT * FROM city_list")
    fun queryCityData():List<CityData>

    @Query("SELECT * FROM city_list WHERE id = :id")
    fun queryCityDataById(id:String):CityData
}