package com.ctwofinalproject.ticketing.admin.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ctwofinalproject.ticketing.admin.api.RestServiceMain
import com.ctwofinalproject.ticketing.admin.model.ResponseGetAllFlight
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ShowListFlightViewModel @Inject constructor(var api: RestServiceMain): ViewModel() {
    var liveDataFlight : MutableLiveData<ResponseGetAllFlight?> = MutableLiveData()
    
    fun getAllFlight(token: String){
        val client = api.getAllFlight(token)
        client.enqueue(object : Callback<ResponseGetAllFlight>{
            override fun onResponse(
                call: Call<ResponseGetAllFlight>,
                response: Response<ResponseGetAllFlight>
            ) {
                if(response.isSuccessful){
                    liveDataFlight.postValue(response.body())
                } else {
                    liveDataFlight.value = null
                }
            }

            override fun onFailure(call: Call<ResponseGetAllFlight>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
    }
    
    
}