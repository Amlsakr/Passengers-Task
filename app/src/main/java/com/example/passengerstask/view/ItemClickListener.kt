package com.example.passengerstask.view

import com.example.passengerstask.data.model.AirLineItem


interface ItemClickListener {
    fun onClickItem(item : AirLineItem)
}