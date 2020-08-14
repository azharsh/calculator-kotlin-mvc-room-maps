package com.example.testcalculator.list

import com.example.testcalculator.model.Data
import com.example.testcalculator.repo.Repository


class ListController(listActivity: ListActivity) {

    var repository = Repository(listActivity)

    suspend fun getAllData(): List<Data> {
        return repository.getAllData()
    }

}