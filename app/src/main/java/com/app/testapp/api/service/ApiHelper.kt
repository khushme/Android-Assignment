package com.app.testapp.api.service

import android.util.Log
import com.app.testapp.model.ErrorsResponse
import com.app.testapp.api.ApiError
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiHelper {
    private var mRetrofit: Retrofit
    private var mUserId: String? = null

    // Creating Retrofit Object
    init {


        mRetrofit = Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build()
    }




    // Creating OkHttpclient Object
    private fun getClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient().newBuilder().connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)

                .build()

    }


    //Creating service class for calling the web services
    fun createService(): WebService {


        return mRetrofit.create(WebService::class.java)
    }




    // Handling error messages returned by Apis
    fun handleApiError(body: ResponseBody?): String {
        var errorMsg = "Something went wrong"
        try {
            val errorConverter: Converter<ResponseBody, ErrorsResponse> = mRetrofit.responseBodyConverter(
                ErrorsResponse::class.java, arrayOfNulls(0))
            val error: ErrorsResponse = errorConverter.convert(body)
            errorMsg = error.status?.errorMessage!!

        } catch (e: Exception) {

            Log.e("error_retrofit", e.toString())
        }

        return errorMsg
    }


    fun handleErrorResponse(
            response: Response<*>?,
            errorHandler: (ApiError) -> Unit) {
        // If there is some validation error
        response?.errorBody()?.let {
            errorHandler(ApiError(handleApiError(it), response.raw().code()))
        } ?: errorHandler(ApiError())
    }

}