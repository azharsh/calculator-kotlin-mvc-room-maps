package com.example.testcalculator.main

import com.example.testcalculator.model.Data
import com.example.testcalculator.repo.Repository


class MainController( mainActivity: MainActivity)  {

    var repository = Repository(mainActivity)

    suspend fun saveDatabase(data: Data){
        repository.saveData(data)
    }

}