package com.example.passengerstask.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.passengerstask.data.model.AirLineItem
import com.example.passengerstask.databinding.AirlineItemBinding
import com.example.passengerstask.view.ItemClickListener

class HomeAdapter (val context : Context, val itemClickListener: ItemClickListener) :
        RecyclerView.Adapter<HomeAdapter.AirLineItemViewHolder> (){
     var airLineItemList  =  ArrayList <AirLineItem>()
    @JvmName("setAirLineItemList1")
    fun setAirLineItemList (airLineItemList: ArrayList<AirLineItem>){
        if (airLineItemList != null) {
            this.airLineItemList = airLineItemList
        }
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirLineItemViewHolder {
        val airLineItemBinding =
                AirlineItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AirLineItemViewHolder(airLineItemBinding)
    }

    override fun onBindViewHolder(holder: AirLineItemViewHolder, position: Int) {
        holder.airLineItemBinding.imageView.setOnClickListener {
            airLineItemList.get(position)?.let { it1 -> itemClickListener.onClickItem(it1) }
        }
        holder.airLineItemBinding.textViewTitle.text = airLineItemList.get(position).name

    }

    override fun getItemCount(): Int = airLineItemList.size

    inner class AirLineItemViewHolder (var airLineItemBinding : AirlineItemBinding):
            RecyclerView.ViewHolder(airLineItemBinding.root)
}