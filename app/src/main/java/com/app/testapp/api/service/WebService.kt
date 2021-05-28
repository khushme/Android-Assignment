package com.app.testapp.api.service

import com.app.testapp.model.LoginRequest
import com.school.kotlin.data.Login.LoginResponse
import retrofit2.Call
import retrofit2.http.*


interface WebService {

    @Headers("Accept: " + "application/json")
    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>


}


