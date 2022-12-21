package com.ctwofinalproject.ticketing.admin.viewmodel

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ctwofinalproject.ticketing.admin.api.RestServiceMain
import com.ctwofinalproject.ticketing.admin.data.AirportBody
import com.ctwofinalproject.ticketing.admin.model.ResponseAirport
import com.ctwofinalproject.ticketing.admin.model.ResponseDefault
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AirportViewModel @Inject constructor(var api : RestServiceMain): ViewModel() {
    var liveDataAirport: MutableLiveData<ResponseAirport?> = MutableLiveData()
    var liveDataAirportSearch: MutableLiveData<ResponseAirport?> = MutableLiveData()
    var liveDataResponseUpdate : MutableLiveData<ResponseDefault?> = MutableLiveData()

    fun getDataAirport(): MutableLiveData<ResponseAirport?> {
        return liveDataAirport
    }

    fun getDataAirportSearch(): MutableLiveData<ResponseAirport?> {
        return liveDataAirportSearch
    }

    fun fetchAirport() {
        val client = api.getAirport()
        client.enqueue(object : Callback<ResponseAirport> {
            override fun onResponse(
                call: Call<ResponseAirport>,
                response: Response<ResponseAirport>
            ) {
                if (response.isSuccessful) {
                    liveDataAirport.postValue(response.body())
                } else {
                    liveDataAirport.postValue(null)
                }
            }

            override fun onFailure(call: Call<ResponseAirport>, t: Throwable) {
                Log.d(ContentValues.TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun searchAirport(query: String) {
        val client = api.searchAirport(query)
        client.enqueue(object : Callback<ResponseAirport> {
            override fun onResponse(
                call: Call<ResponseAirport>,
                response: Response<ResponseAirport>
            ) {
                if (response.isSuccessful) {
                    liveDataAirportSearch.postValue(response.body())
                } else {
                    liveDataAirportSearch.postValue(null)
                }
            }

            override fun onFailure(call: Call<ResponseAirport>, t: Throwable) {
                Log.d(ContentValues.TAG, "onFailure: ${t.message}")
            }

        })

    }

    fun updateAirport(token : String, idAirport : String,body: AirportBody){
        val client = api.updateAirport(token,idAirport,body)
        client.enqueue(object : Callback<ResponseDefault>{
            override fun onResponse(
                call: Call<ResponseDefault>,
                response: Response<ResponseDefault>
            ) {
                if(response.isSuccessful){
                    liveDataResponseUpdate.postValue(response.body())
                } else {
                    liveDataResponseUpdate.value = null
                }
            }

            override fun onFailure(call: Call<ResponseDefault>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }
}