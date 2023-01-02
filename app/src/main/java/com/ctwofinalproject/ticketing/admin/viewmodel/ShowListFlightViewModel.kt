package com.ctwofinalproject.ticketing.admin.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ctwofinalproject.ticketing.admin.api.RestServiceMain
import com.ctwofinalproject.ticketing.admin.model.ResponseDeleteFlight
import com.ctwofinalproject.ticketing.admin.model.ResponseGetAllFlight
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ShowListFlightViewModel @Inject constructor(var api: RestServiceMain): ViewModel() {
    var liveDataFlight : MutableLiveData<ResponseGetAllFlight?> = MutableLiveData()
    var liveDataDeleteFlight : MutableLiveData<ResponseDeleteFlight?> = MutableLiveData()
    
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

    fun deleteFlight(token: String,idFlight : Int){
        val client = api.deleteFlight(token, idFlight)
        client.enqueue(object : Callback<ResponseDeleteFlight>{
            override fun onResponse(
                call: Call<ResponseDeleteFlight>,
                response: Response<ResponseDeleteFlight>
            ) {
                if(response.isSuccessful){
                    liveDataDeleteFlight.postValue(response.body())
                } else {
                    liveDataDeleteFlight.value = null
                }
            }

            override fun onFailure(call: Call<ResponseDeleteFlight>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }


        })
    }
    
    
}