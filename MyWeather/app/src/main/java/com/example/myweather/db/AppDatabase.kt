package com.example.myweather.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myweather.CityData

//add DB
@Database(entities = [CityData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context) = INSTANCE ?: synchronized(this) { // 동기화블록, 한번에 한사람만
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "weather.db"
            ).build()
            INSTANCE = instance
            return instance
        }
    }
}