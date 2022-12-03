package com.ctwofinalproject.ticketing.admin.api

import com.ctwofinalproject.ticketing.admin.data.ResponseProvinceItem
import com.ctwofinalproject.ticketing.admin.model.ResponseCityItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RestServiceProvince {
    @GET("api/provinces.json")
    fun getProvinces(): Call<List<ResponseProvinceItem>>

    @GET("api/regencies/{id}.json")
    fun getCity(
        @Path("id") id : Int
    ) : Call<List<ResponseCityItem>>
}