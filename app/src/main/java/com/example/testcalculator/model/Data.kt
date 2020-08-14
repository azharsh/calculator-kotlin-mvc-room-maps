package com.example.testcalculator.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Data(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var hasil: Int?,
    var waktu: String?,
    var address: String?,
    var lat: String?,
    var long: String?
){ constructor() : this(0,0,"","","0.0","0.0")}