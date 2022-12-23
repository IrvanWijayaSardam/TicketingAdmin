package com.ctwofinalproject.ticketing.admin.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ctwofinalproject.ticketing.admin.api.RestServiceMain
import com.ctwofinalproject.ticketing.admin.model.ResponseListUserBooking
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ListUserBookingViewModel @Inject constructor(var api : RestServiceMain): ViewModel(){
    var liveDataUserBooking : MutableLiveData<ResponseListUserBooking?> = MutableLiveData()

    fun getListUserBooking(){
        fun getUserBooking(){
            val client = api.getUserBooking()
            client.enqueue(object : Callback<ResponseListUserBooking>{
                override fun onResponse(
                    call: Call<ResponseListUserBooking>,
                    response: Response<ResponseListUserBooking>
                ) {
                    if(response.isSuccessful){
                        liveDataUserBooking.postValue(response.body())
                    } else {
                        liveDataUserBooking.value = null
                    }
                }

                override fun onFailure(call: Call<ResponseListUserBooking>, t: Throwable) {
                    Log.d(ContentValues.TAG, "onFailure: ${t.message}")
                }

            })
        }
    }
}