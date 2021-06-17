package com.example.passengerstask.data.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "airLineTable")
@Parcelize
data class AirLineItem(@SerializedName("established")
                       val established: String? = null,
                       @SerializedName("country")
                       val country: String? = null,
                       @SerializedName("website")
                       val website: String? = null,
                       @SerializedName("name")
                       val name: String? = null,
                       @SerializedName("head_quaters")
                       val headQuaters: String? = null,
                       @SerializedName("logo")
                       val logo: String? = null,
                       @NonNull
                       @SerializedName("id")
                       @PrimaryKey
                       val id: Double ,
                       @SerializedName("slogan")
                       val slogan: String? = null ) : Parcelable