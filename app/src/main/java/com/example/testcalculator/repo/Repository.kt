package com.example.testcalculator.repo

import android.content.Context
import com.example.testcalculator.database.AppDatabase
import com.example.testcalculator.model.Data
import com.example.testcalculator.model.DataDao


class Repository( context:Context) {

     var dataDao: DataDao

     var db: AppDatabase

    init {
        db = AppDatabase.getInstance(context)
        dataDao = db.dataDao()
    }

    suspend fun saveData(data:Data){
        dataDao.insert(data)
    }

    suspend fun getAllData(): List<Data> {
      return dataDao.getAll()
    }

    suspend fun getDataById(id: Int): Data {
        return dataDao.getDataById(id)
    }

}