package com.app.testapp.login


import androidx.lifecycle.MutableLiveData
import com.app.testapp.api.model.MyViewModel
import com.app.testapp.model.LoginRequest
import com.school.kotlin.data.Login.LoginResponse
import com.school.kotlin.ui.login.LoginRepository


class LoginViewModel : MyViewModel() {

    var response = MutableLiveData<LoginResponse>()


    fun loginData(request: LoginRequest) {
        isLoading.value = true
        LoginRepository.loginUser({
            response.value = it
            isLoading.value = false
        }, {
            mApiError.value = it
            isLoading.value = false
        }, {
            onFailure.value = it
            isLoading.value = false
        }, request)
    }


}