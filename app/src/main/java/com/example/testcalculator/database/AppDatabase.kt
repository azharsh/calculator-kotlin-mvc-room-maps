package com.example.testcalculator.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.testcalculator.model.Data
import com.example.testcalculator.model.DataDao


@Database(entities = [Data::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dataDao(): DataDao


    companion object {

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

            }
        }

        fun getInstance(context: Context): AppDatabase = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "room-name"
        ).addMigrations(MIGRATION_1_2).build()

    }


}