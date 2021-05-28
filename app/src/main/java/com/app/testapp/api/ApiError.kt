package com.app.testapp.api

/**
 * Created by Arun on 30/4/18.
 * ApiError  contains api error code and messages
 */
data class ApiError(
        var errorMessage: String? = null,
        var errorCode: Int? = null

)