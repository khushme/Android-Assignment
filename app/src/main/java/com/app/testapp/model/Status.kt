package com.app.testapp.model


import com.google.gson.annotations.SerializedName

data class Status(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("errorMessage")
	val errorMessage: String? = null
)