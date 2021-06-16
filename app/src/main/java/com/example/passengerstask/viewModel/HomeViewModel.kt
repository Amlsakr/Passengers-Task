package com.example.passengerstask.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passengerstask.data.model.AirLineItem
import com.example.passengerstask.data.repository.MainRepository

//import com.example.passengerstask.data.repository.MainRepository
import com.example.passengerstask.data.utilities.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
//import com.example.passengerstask.data.utilities.Resource
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(private val mainRepository: MainRepository) : ViewModel() {
    private val _res = MutableLiveData<Resource<List<AirLineItem>>>()
    val res: LiveData<Resource<List<AirLineItem>>>
        get() = _res

//    private val _local = MutableLiveData<List<JobModel>>()
//    val local: LiveData<List<JobModel>>
//        get() = _local

    init {
        getJobsFromInternet()
    }


     fun getJobsFromInternet() = viewModelScope.launch(Dispatchers.IO) {
     _res.postValue(Resource.loading(null))
        mainRepository.getJobsFomInternet().let {
            if (it.isSuccessful) {
                _res.postValue(Resource.success(it.body()))
            } else {
                _res.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }




//    fun getData() {
//        var retrofitClient: RetrofitClient = RetrofitClient
//        val call = retrofitClient.getAirLines()
//        Log.e("aml url" , call.request().url.toString() )
//        call.enqueue(object :Callback<List<AirLineItem>>{
//            override fun onResponse(call: Call<List<AirLineItem>>, response: Response<List<AirLineItem>>) {
//           _res.postValue(response.body())
//                Log.e("aml" ,Gson().toJson( response.body().toString()))
//            }
//
//            override fun onFailure(call: Call<List<AirLineItem>>, t: Throwable) {
//                t.printStackTrace()
//                Log.e("aml", "onFailure: "+t.toString() )
//            }
//
//        })
//    }
}

