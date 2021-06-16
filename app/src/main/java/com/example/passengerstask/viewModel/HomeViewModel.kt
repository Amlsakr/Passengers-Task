package com.example.passengerstask.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passengerstask.data.model.AirLineItem
import com.example.passengerstask.data.repository.MainRepository
import com.example.passengerstask.data.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(private val mainRepository: MainRepository) : ViewModel() {
    private val _res = MutableLiveData<Resource<List<AirLineItem>>>()
    val res: LiveData<Resource<List<AirLineItem>>>
        get() = _res

    private val _local = MutableLiveData<List<AirLineItem>>()
    val local: LiveData<List<AirLineItem>>
        get() = _local

    init {
        getJobsFromInternet()
    }


     fun getJobsFromInternet() = viewModelScope.launch(Dispatchers.IO) {
     _res.postValue(Resource.loading(null))
        mainRepository.getJobsFomInternet().let {
            if (it.isSuccessful) {
                _res.postValue(Resource.success(it.body()))
                it.body()?.let { it1 ->
                    for (item in it1){
                        if(item.id != 0.0)
                            mainRepository.insertItem(item)
                    }
                }
                getJobsFromDataBase()
            } else {
                _res.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }




    private suspend fun getJobsFromDataBase(): ArrayList<AirLineItem> {
        var list: ArrayList<AirLineItem> = arrayListOf()
        _res.postValue(Resource.loading(null))
        mainRepository.getAll().let {
            Log.e("tag", "size of array from DB" + it.size)
            _local.postValue(it)
            list = it as ArrayList<AirLineItem>
        }
        return list
    }
}

