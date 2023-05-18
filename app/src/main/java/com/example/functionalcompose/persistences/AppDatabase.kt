package com.example.functionalcompose.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.functionalcompose.model.SetoranSampah

@Database(entities = [SetoranSampah::class], version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun setoranSampahDao(): SetoranSampahDao
}