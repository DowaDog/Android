package com.takhyungmin.dowadog.signup.model

import android.util.Log
import com.takhyungmin.dowadog.home.model.get.GetDuplicateResponse
import com.takhyungmin.dowadog.signup.SignObject
import com.takhyungmin.dowadog.signup.model.post.PostSignIdSettingResponse
import com.takhyungmin.dowadog.utils.ApplicationData
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignUpModel {

    private var signUpNetworkService : SignUpNetworkService

    init {

        val builder = Retrofit.Builder()
        val retrofit = builder
                .baseUrl(ApplicationData.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        signUpNetworkService = retrofit.create(SignUpNetworkService::class.java)
    }



    fun postSignIdSettingData(id : String, password : String, name : String, birth : String,
                              phone : String, email : String, gender : String, deviceToken : String,
                              type : String, mimg: MultipartBody.Part?, pushAllow : String) {
        Log.v("sign", "들어옴")
        val cId = RequestBody.create(MediaType.parse("text/plain"), id)
        val cPassword = RequestBody.create(MediaType.parse("text/plain"), password)
        val cName = RequestBody.create(MediaType.parse("text/plain"), name)
        val cBirth = RequestBody.create(MediaType.parse("text/plain"), birth)
        val cPhone = RequestBody.create(MediaType.parse("text/plain"), phone)
        val cEmail = RequestBody.create(MediaType.parse("text/plain"), email)
        val cGender = RequestBody.create(MediaType.parse("text/plain"), gender)
        val cDeviceToken = RequestBody.create(MediaType.parse("text/plain"), deviceToken)
        val cType = RequestBody.create(MediaType.parse("text/plain"), type)
        val cPushAllow = RequestBody.create(MediaType.parse("text/plain"), pushAllow)
        signUpNetworkService.postSignIdSettingResponse( cId, cPassword, cName, cBirth,
                cPhone, cEmail, cGender, cDeviceToken, cType, mimg, cPushAllow)
                .enqueue(object : Callback<PostSignIdSettingResponse> {
                    override fun onFailure(call: Call<PostSignIdSettingResponse>?, t: Throwable?) {
                        Log.e("회원가입 통신실패", t.toString())
                    }
                    override fun onResponse(call: Call<PostSignIdSettingResponse>?, response: Response<PostSignIdSettingResponse>?) {

                            if(response!!.isSuccessful){
                                Log.v("sign", "success")
                                SignObject.signIdSettingActivityPresenter.responseData(id, password)
                            }else{
                                Log.v("sign", "fail")

                            }
                    }
                })
    }

    fun getDuplicateData(id : String){
        signUpNetworkService.checkDuplicate(id).enqueue(object : Callback<GetDuplicateResponse> {
            override fun onFailure(call: Call<GetDuplicateResponse>, t: Throwable) {
                Log.v("fail", t.toString())
            }

            override fun onResponse(call: Call<GetDuplicateResponse>, response: Response<GetDuplicateResponse>) {
                if(response.isSuccessful){
                    Observable.just(response.body())
                            .subscribe { s-> SignObject.signIdSettingActivityPresenter.responseDuplicateData(s!!)}
                }
            }
        })
    }


}