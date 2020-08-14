package com.example.testcalculator.list

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testcalculator.R
import com.example.testcalculator.map.MapsActivity
import com.example.testcalculator.model.DataModel
import kotlinx.android.synthetic.main.item_list.view.*


class ListAdapter(val listActivity: ListActivity) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    var datalist = mutableListOf<DataModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.hasil.text = "Hasil : " + datalist[position].hasil
        holder.waktu.text = "Waktu : " + datalist[position].waktu
        holder.address.text = "Address : " + datalist[position].address
        holder.latitude.text = "Latitude : " + datalist[position].lat
        holder.longitude.text = "Longitude : " + datalist[position].long

        holder.itemView.setOnClickListener {
            val intent = Intent(listActivity,MapsActivity::class.java )
            intent.putExtra("id", datalist[position].id)
            listActivity.startActivity(intent)
        }

    }

    fun setList(newlist: ArrayList<DataModel>){
        this.datalist.clear()
        this.datalist.addAll(newlist)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val hasil = itemView.tvHasil
        val waktu = itemView.tvWaktu
        val address = itemView.tvAdress
        val latitude = itemView.tvLat
        val longitude = itemView.tvLong

    }
}