package com.ctwofinalproject.ticketing.admin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ctwofinalproject.ticketing.admin.repository.UserPreferencesRepository
import kotlinx.coroutines.launch

class ProtoViewModel(application: Application) :AndroidViewModel(application) {
    private val repo = UserPreferencesRepository(application)
    val dataUser = repo.readProto.asLiveData()

    fun editData(firstname: String,lastname : String,gender : String,email : String,phone : String,birthdate : String,pictures : String,token : String, isLogin: Boolean) = viewModelScope.launch{
        repo.saveData(firstname, lastname, gender, email, phone,birthdate, pictures, token, isLogin)
    }

    fun clearData() = viewModelScope.launch {
        repo.deleteData()
    }
}