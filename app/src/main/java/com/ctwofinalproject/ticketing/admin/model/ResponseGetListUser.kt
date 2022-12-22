package com.ctwofinalproject.ticketing.admin.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ResponseGetListUser(

	@field:SerializedName("msg")
	val msg: String? = null,

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: List<DataUserItem?>? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
) : Parcelable

@Parcelize
data class Roles(

	@field:SerializedName("roleName")
	val roleName: String? = null
) : Parcelable

@Parcelize
data class DataUserItem(

	@field:SerializedName("firstname")
	val firstname: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("address")
	val address: Address? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("roles")
	val roles: Roles? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("pictures")
	val pictures: String? = null,

	@field:SerializedName("lastname")
	val lastname: String? = null
) : Parcelable

@Parcelize
data class Address(

	@field:SerializedName("province")
	val province: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("homeAddress")
	val homeAddress: String? = null
) : Parcelable
