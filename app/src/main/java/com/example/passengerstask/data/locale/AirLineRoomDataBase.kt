package com.example.passengerstask.data.locale

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.passengerstask.data.model.AirLineItem
import javax.inject.Singleton

@Singleton
@Database(entities = arrayOf(AirLineItem::class ) , version = 1 ,exportSchema = false)
abstract  class AirLineRoomDataBase : RoomDatabase() {

    abstract fun airLineDao () : AirLineDao
    companion object{

        @Volatile
        private var INSTANCE : AirLineRoomDataBase? = null

        fun getDataBase (context : Context) : AirLineRoomDataBase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext , AirLineRoomDataBase::class.java , "job_database" )
                        .build()
                Log.e("TAG", "getDataBase: " + "DataBase Created" )
                INSTANCE = instance
                instance

            }

        }
    }
}