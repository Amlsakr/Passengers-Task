package com.example.passengerstask.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.passengerstask.data.model.AirLineItem
import com.example.passengerstask.databinding.AirlineItemBinding
import com.example.passengerstask.view.ItemClickListener

class HomeAdapter (val context : Context, val itemClickListener: ItemClickListener) :
        RecyclerView.Adapter<HomeAdapter.AirLineItemViewHolder> () , Filterable{
     var airLineItemList  =  ArrayList <AirLineItem>()
     var filteredairLineItemList  =  ArrayList <AirLineItem>()
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

    @ExperimentalStdlibApi
    override fun getFilter(): Filter {

        return object  : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                var searchKey = constraint.toString()
                if(searchKey.isEmpty()){
                    filteredairLineItemList = airLineItemList
                }else {
                    var filterList =  ArrayList <AirLineItem>()
                    for (item in airLineItemList){
                        if (item.name?.lowercase()?.contains(searchKey.lowercase()) == true ||
                                item.country?.lowercase()?.contains(searchKey.lowercase()) == true ||
                                item.id == searchKey.toDouble()       ){
                            filterList.add(item)
                        }
                    }
                    filteredairLineItemList = filterList
                }

                var filterResult = FilterResults()
                filterResult.values = filteredairLineItemList
                return  filterResult
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredairLineItemList = results?.values as ArrayList<AirLineItem>
                notifyDataSetChanged()
            }

        }

    }
}