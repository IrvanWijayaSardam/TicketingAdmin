package com.ctwofinalproject.ticketing.admin.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ctwofinalproject.ticketing.admin.api.RestServiceMain
import com.ctwofinalproject.ticketing.admin.data.Login
import com.ctwofinalproject.ticketing.admin.model.ResponseLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(var api : RestServiceMain): ViewModel() {
    var liveDataUser = MutableLiveData<ResponseLogin?>()

    init {
        liveDataUser = MutableLiveData()
    }

    fun getToken() : LiveData<ResponseLogin?> {
        return  liveDataUser
    }

    fun auth(login: Login){
        val client = api.auth(login)
        client.enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                val responseBody = response.body()
                if(response.isSuccessful) {
                    Log.d(ContentValues.TAG, "onResponse: Response Success")
                    liveDataUser.postValue(responseBody)
                } else {
                    liveDataUser.value = null
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                Log.d(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }

}