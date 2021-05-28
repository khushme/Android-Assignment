package com.school.kotlin.ui.login

import com.app.testapp.api.ApiError
import com.app.testapp.api.service.ApiHelper

import com.app.testapp.model.LoginRequest
import com.school.kotlin.data.Login.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Data Provider for the application
 */
object LoginRepository {

    fun loginUser(successHandler: (LoginResponse) -> Unit,
                  errorHandler: (ApiError) -> Unit,
                  onFaliureHandler: (Throwable) -> Unit,
                  request: LoginRequest
    ) {
        val webService = ApiHelper.createService()
        webService.login(request).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {
                response?.body()?.let {
                    successHandler(it)
                }?: ApiHelper.handleErrorResponse(response, errorHandler)
            }

            override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
                t?.let {
                    onFaliureHandler(it)
                }
            }
        })
    }
}