package com.ctwofinalproject.ticketing.admin.api

import com.ctwofinalproject.ticketing.admin.data.AirportBody
import com.ctwofinalproject.ticketing.admin.data.FlightBody
import com.ctwofinalproject.ticketing.admin.data.Login
import com.ctwofinalproject.ticketing.admin.data.User
import com.ctwofinalproject.ticketing.admin.model.*
import retrofit2.Call
import retrofit2.http.*

interface RestServiceMain {

        @POST("api/register")
        fun createUser(
            @Body body : User
        ) : Call<ResponseMessage>

        @POST("api/login")
        fun auth(
            @Body body: Login
        ) : Call<ResponseLogin>

        @GET("api/airports")
        fun getAirport(
        ) : Call<ResponseAirport>

        @GET("api/airports/{query}")
        fun searchAirport(
            @Path("query") query : String
        ) : Call<ResponseAirport>

        @POST("api/flight/create")
        fun createFlight(
            @Body body : FlightBody
        ): Call<ResponseFlight>

        @GET("api/flight")
        fun getAllFlight() : Call<ResponseGetAllFlight>

        @POST("api/flight/create")
        fun updateFlight(
                @Body body: FlightBody
        ): Call<ResponseFlight>

        @GET("api/users/")
        fun getAllUser(
        ):Call<ResponseGetListUser>

        @PUT("api/airports/edit/{query}")
        fun updateAirport(
                @Header("Authorization") autorization : String,
                @Path("query") query : String,
                @Body body : AirportBody
        ) : Call<ResponseDefault>

}
