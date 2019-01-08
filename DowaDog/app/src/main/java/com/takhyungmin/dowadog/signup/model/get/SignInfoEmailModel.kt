package com.takhyungmin.dowadog.signup.model.get

import android.util.Log
import com.takhyungmin.dowadog.signup.SignObject
import com.takhyungmin.dowadog.signup.model.SignInfoEmailNetworkService
import com.takhyungmin.dowadog.utils.ApplicationData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignInfoEmailModel {
    private var signInfoEmailNetworkService : SignInfoEmailNetworkService

    init {

        val builder = Retrofit.Builder()
        val retrofit = builder
                .baseUrl(ApplicationData.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        signInfoEmailNetworkService = retrofit.create(SignInfoEmailNetworkService::class.java)
    }

    fun getSignInfoWriteData(email: String){
        signInfoEmailNetworkService.GetSignInfoEmailResponse("tae@gmail.com")
                .enqueue(object: Callback<GetSignInfoEmailResponse> {
            override fun onFailure(call: Call<GetSignInfoEmailResponse>?, t: Throwable?) {
                Log.e("SignInfo 이메일 중복확인 통신실패", t.toString())
            }

            override fun onResponse(call: Call<GetSignInfoEmailResponse>?, response: Response<GetSignInfoEmailResponse>?) {

                response?.takeIf { it.isSuccessful }
                        ?.body()
                        ?.let {
                            SignObject.SignInfoWriteActivityPresenter.responseData(it)
                        }
            }
        })
    }


}