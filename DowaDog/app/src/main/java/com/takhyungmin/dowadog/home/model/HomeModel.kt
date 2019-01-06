package com.takhyungmin.dowadog.home.model

import android.util.Log
import com.takhyungmin.dowadog.home.HomeObject
import com.takhyungmin.dowadog.home.model.get.GetDuplicateResponse
import com.takhyungmin.dowadog.home.model.get.GetUserInfoResponse
import com.takhyungmin.dowadog.utils.ApplicationData
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeModel {
    private var homeNetworkSerVice : HomeNetworkService

    init{
        val builder = Retrofit.Builder()
        val retrofit = builder
                .baseUrl(ApplicationData.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        homeNetworkSerVice = retrofit.create(HomeNetworkService::class.java)
    }


    fun getDuplicateData(id : String){
        homeNetworkSerVice.checkDuplicate(id).enqueue(object : Callback<GetDuplicateResponse> {
            override fun onFailure(call: Call<GetDuplicateResponse>, t: Throwable) {
                Log.v("fail", t.toString())
            }

            override fun onResponse(call: Call<GetDuplicateResponse>, response: Response<GetDuplicateResponse>) {
                if(response.isSuccessful){
                    Observable.just(response.body())
                            .subscribe { s-> HomeObject.homeActivityPresenter.responseData(s!!)}
                }
            }
        })
    }

    fun getUserInfo(){
        homeNetworkSerVice.getUserInfo(ApplicationData.auth).enqueue(object : Callback<GetUserInfoResponse>{
            override fun onFailure(call: Call<GetUserInfoResponse>, t: Throwable) {
                Log.v("fail", t.toString())
            }
            override fun onResponse(call: Call<GetUserInfoResponse>, response: Response<GetUserInfoResponse>) {
                if(response.isSuccessful){
                    HomeObject.homeActivityPresenter.responseUserInfo(response.body()!!.data)
                }
            }
        })
    }
}
