package com.ctwofinalproject.ticketing.admin.data

import com.google.gson.annotations.SerializedName

data class UserBody(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("firstname")
	val firstname: String? = null,

	@field:SerializedName("birthdate")
	val birthdate: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("province")
	val province: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("pictures")
	val pictures: String? = null,

	@field:SerializedName("lastname")
	val lastname: String? = null,

	@field:SerializedName("homeAddress")
	val homeAddress: String? = null
)
