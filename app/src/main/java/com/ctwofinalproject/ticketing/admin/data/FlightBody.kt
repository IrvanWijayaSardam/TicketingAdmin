package com.ctwofinalproject.ticketing.admin.data

import com.google.gson.annotations.SerializedName

data class FlightBody(

	@field:SerializedName("flight")
	val flight: Flight? = null,

	@field:SerializedName("ticket")
	val ticket: Ticket? = null
)

data class Flight(

	@field:SerializedName("departureTime")
	val departureTime: String? = null,

	@field:SerializedName("departureAirport")
	val departureAirport: Int? = null,

	@field:SerializedName("arrivalTime")
	val arrivalTime: String? = null,

	@field:SerializedName("planeId")
	val planeId: Int? = null,

	@field:SerializedName("departureDate")
	val departureDate: String? = null,

	@field:SerializedName("flightType")
	val flightType: Int? = null,

	@field:SerializedName("arrivalAirport")
	val arrivalAirport: Int? = null,

	@field:SerializedName("arrivalDate")
	val arrivalDate: String? = null
)

data class Ticket(

    @field:SerializedName("country")
    val country: String? = null,

    @field:SerializedName("price")
    val price: Int? = null,

    @field:SerializedName("class_id")
    val classId: Int? = null
)
