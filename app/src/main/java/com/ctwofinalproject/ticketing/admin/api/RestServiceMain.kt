package com.ctwofinalproject.ticketing.admin.api

import com.ctwofinalproject.ticketing.admin.data.*
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
            @Header("Authorization") autorization: String,
            @Body body : FlightBody
        ): Call<ResponseFlight>

        @GET("api/flight")
        fun getAllFlight(
                @Header("Authorization") autorization: String
        ) : Call<ResponseGetAllFlight>

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

        @PUT("api/users/edit/{query}")
        fun updateUser(
                @Header("Authorization") autorization: String,
                @Path("query") query: String,
                @Body body: UserBody
        ) : Call<ResponseDefault>

        @GET("api/userbookings")
        fun getUserBooking(
                @Header("Authorization") autorization: String
        ):Call<ResponseListUserBooking>

}
