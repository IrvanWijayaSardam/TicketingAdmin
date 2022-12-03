package com.ctwofinalproject.ticketing.admin.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

class User(
    @SerializedName("email")
    val email: String?,
    @SerializedName("firstname")
    val firstname: String?,
    @SerializedName("lastname")
    val lastname: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("birthdate")
    val birthdate: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("confPassword")
    val confPassword: String?
)