package com.ctwofinalproject.ticketing.admin.data

import com.google.gson.annotations.SerializedName

data class AirportBody(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("terminal")
	val terminal: String? = null
)
