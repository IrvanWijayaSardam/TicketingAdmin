package com.ctwofinalproject.ticketing.admin.model

import com.google.gson.annotations.SerializedName

data class ResponseDeleteFlight(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)
