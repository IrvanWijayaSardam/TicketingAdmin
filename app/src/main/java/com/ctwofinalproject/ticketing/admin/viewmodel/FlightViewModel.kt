package com.ctwofinalproject.ticketing.admin.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.ctwofinalproject.ticketing.admin.api.RestServiceMain
import com.ctwofinalproject.ticketing.admin.data.FlightBody
import com.ctwofinalproject.ticketing.admin.model.ResponseFlight
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FlightViewModel @Inject constructor(var api : RestServiceMain): ViewModel(){


    fun createFlight(flightBody: FlightBody){
        val client = api.createFlight(flightBody)
        client.enqueue(object : Callback<ResponseFlight>{
            override fun onResponse(
                call: Call<ResponseFlight>,
                response: Response<ResponseFlight>
            ) {
                if (response.isSuccessful){
                    Log.d(TAG, "onResponse: create succses")
                }else{
                    Log.d(TAG, "onResponse: create failed")
                }
            }

            override fun onFailure(call: Call<ResponseFlight>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

}