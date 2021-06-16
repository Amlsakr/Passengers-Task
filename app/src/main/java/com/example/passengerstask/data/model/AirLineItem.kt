package com.example.passengerstask.data.model

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "airLineTable")
@Parcelize
data class AirLineItem(@SerializedName("established")
                       val established: String = "",
                       @SerializedName("country")
                       val country: String = "",
                       @SerializedName("website")
                       val website: String = "",
                       @SerializedName("name")
                       val name: String = "",
                       @SerializedName("head_quaters")
                       val headQuaters: String = "",
                       @SerializedName("logo")
                       val logo: String = "",
                       @SerializedName("id")
                       val id: Double = 0.0,
                       @SerializedName("slogan")
                       val slogan: String = "") : Parcelable