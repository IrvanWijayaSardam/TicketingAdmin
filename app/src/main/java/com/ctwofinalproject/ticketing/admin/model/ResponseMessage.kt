package com.ctwofinalproject.ticketing.admin.model

import com.google.gson.annotations.SerializedName

data class ResponseMessage(

	@field:SerializedName("msg")
	val msg: String? = null
)
