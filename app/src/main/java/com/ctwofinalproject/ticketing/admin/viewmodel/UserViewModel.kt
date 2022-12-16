package com.ctwofinalproject.ticketing.admin.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ctwofinalproject.ticketing.admin.api.RestServiceMain
import com.ctwofinalproject.ticketing.admin.model.ResponseGetListUser
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(var api : RestServiceMain):ViewModel() {
    var liveDataUser : MutableLiveData<ResponseGetListUser?> = MutableLiveData()

    fun getAllUser(){
        val client = api.getAllUser()
        client.enqueue(object : Callback<ResponseGetListUser>{
            override fun onResponse(
                call: Call<ResponseGetListUser>,
                response: Response<ResponseGetListUser>
            ) {
                if(response.isSuccessful){
                    liveDataUser.postValue(response.body())
                } else {
                    liveDataUser.value = null
                }
            }

            override fun onFailure(call: Call<ResponseGetListUser>, t: Throwable) {
                Log.d(ContentValues.TAG, "onFailure: ${t.message}")
            }


        })
    }
}