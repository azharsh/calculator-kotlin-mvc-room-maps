package com.example.testcalculator.map

import com.example.testcalculator.model.Data
import com.example.testcalculator.repo.Repository


class MapsController(mapsActivity: MapsActivity) {

    var repository = Repository(mapsActivity)

    suspend fun getDataById(id:Int): Data {
        return repository.getDataById(id)

    }


}