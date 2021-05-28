package com.app.testapp.api.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.testapp.api.ApiError


open class MyViewModel : ViewModel() {
    var onFailure = MutableLiveData<Throwable>()
    var result = MutableLiveData<String>()
    var isLoading = MutableLiveData<Boolean>()
    var mApiError = MutableLiveData<ApiError>()

}