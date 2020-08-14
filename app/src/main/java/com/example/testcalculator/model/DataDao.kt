package com.example.testcalculator.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

import androidx.room.Query

@Dao
interface DataDao{
    @Query("SELECT * FROM data")
    suspend fun getAll(): List<Data>

    @Insert()
    suspend fun insert(vararg data: Data)

    @Query("SELECT * FROM data WHERE id = :id")
    suspend fun getDataById(id: Int): Data


}