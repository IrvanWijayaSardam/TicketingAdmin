package com.ctwofinalproject.ticketing.admin.model

import com.google.gson.annotations.SerializedName

data class ResponseListUserBooking(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class Users(

	@field:SerializedName("firstname")
	val firstname: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("lastname")
	val lastname: String? = null
)

data class TicketDeparture(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("class_id")
	val classId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("flight_id")
	val flightId: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class PassangerBookingItem(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("idBooking")
	val idBooking: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("idPassanger")
	val idPassanger: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class TicketReturn(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("class_id")
	val classId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("flight_id")
	val flightId: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class DataItemUserBooking(

	@field:SerializedName("booking_id")
	val bookingId: Int? = null,

	@field:SerializedName("booking")
	val booking: Booking? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("users")
	val users: Users? = null
)

data class Booking(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("isBooking")
	val isBooking: Boolean? = null,

	@field:SerializedName("ticketReturn")
	val ticketReturn: TicketReturn? = null,

	@field:SerializedName("ticket_id_departure")
	val ticketIdDeparture: Int? = null,

	@field:SerializedName("totalPassanger")
	val totalPassanger: Int? = null,

	@field:SerializedName("ticketDeparture")
	val ticketDeparture: TicketDeparture? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("ticket_id_return")
	val ticketIdReturn: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null,

	@field:SerializedName("passangerBooking")
	val passangerBooking: List<Any?>? = null
)
