package com.ctwofinalproject.ticketing.admin.model

import com.google.gson.annotations.SerializedName

data class ResponseGetAllFlight(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItemFlight?>? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class DepartureTerminal(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("terminal")
	val terminal: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class DataItemFlight(

	@field:SerializedName("departureTime")
	val departureTime: String? = null,

	@field:SerializedName("departureAirport")
	val departureAirport: Int? = null,

	@field:SerializedName("planename")
	val planename: Planename? = null,

	@field:SerializedName("DepartureTerminal")
	val departureTerminal: DepartureTerminal? = null,

	@field:SerializedName("ArrivalTerminal")
	val arrivalTerminal: ArrivalTerminal? = null,

	@field:SerializedName("arrivalDate")
	val arrivalDate: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("arrivalTime")
	val arrivalTime: String? = null,

	@field:SerializedName("planeId")
	val planeId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("departureDate")
	val departureDate: String? = null,

	@field:SerializedName("arrivalAirport")
	val arrivalAirport: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class ArrivalTerminal(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("terminal")
	val terminal: String? = null,

	@field:SerializedName("status")
	val status: Boolean? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class Planename(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("namePlane")
	val namePlane: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
