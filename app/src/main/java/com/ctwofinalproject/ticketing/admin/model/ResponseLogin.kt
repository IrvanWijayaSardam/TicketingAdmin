package com.ctwofinalproject.ticketing.admin.model

import com.google.gson.annotations.SerializedName

data class ResponseLogin(

	@field:SerializedName("accessToken")
	val accessToken: String? = null
)
