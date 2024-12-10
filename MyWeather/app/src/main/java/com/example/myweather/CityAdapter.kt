package com.example.myweather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather.databinding.LayoutCityItemBinding

class CityAdapter(val citydata: ArrayList<CityData>):
    RecyclerView.Adapter<CityAdapter.CityViewHolder>() {
    inner class CityViewHolder(val binding: LayoutCityItemBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        var view = LayoutCityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return citydata.size
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.binding.name.text = citydata[position].name
    }
}