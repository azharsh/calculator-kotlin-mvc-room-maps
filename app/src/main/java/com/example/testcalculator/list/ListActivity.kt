package com.example.testcalculator.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testcalculator.R
import com.example.testcalculator.database.AppDatabase
import com.example.testcalculator.model.DataDao
import com.example.testcalculator.model.DataModel
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListActivity : AppCompatActivity() {

    private lateinit var listAdapter: ListAdapter

    private  var dataModel = mutableListOf<DataModel>()

    private lateinit var listController: ListController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        listController = ListController(this)

        listAdapter = ListAdapter(this)
        reclist.layoutManager = LinearLayoutManager(this)
        reclist.adapter = listAdapter

        launch {

            listController.getAllData().forEach {
                dataModel.add(
                    DataModel(
                        it.id,
                        it.hasil,
                        it.waktu,
                        it.address,
                        it.lat,
                        it.long
                    )
                )
            }

            listAdapter.setList(dataModel as ArrayList<DataModel>)

        }

    }

    fun launch(context: CoroutineContext = Dispatchers.Main, block: suspend () -> Unit) {
        CoroutineScope(context).launch {
            block()
        }
    }


}
