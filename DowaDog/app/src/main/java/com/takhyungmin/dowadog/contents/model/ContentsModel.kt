package com.takhyungmin.dowadog.contents.model

import android.util.Log
import com.takhyungmin.dowadog.contents.ContentsObject
import com.takhyungmin.dowadog.contents.model.get.GetEduContentsResponse
import com.takhyungmin.dowadog.utils.ApplicationData
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ContentsModel {
    private var contentsNetworkService : ContentsNetworkService

    init{
        val builder = Retrofit.Builder()
        val retrofit = builder
                .baseUrl(ApplicationData.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        contentsNetworkService = retrofit.create(ContentsNetworkService::class.java)
    }

    fun requestList(){
        contentsNetworkService.getEduContentsList(ApplicationData.auth).enqueue(object : Callback<GetEduContentsResponse>{
            override fun onFailure(call: Call<GetEduContentsResponse>, t: Throwable) {
                Log.v("ConetnstList", t.toString())
            }

            override fun onResponse(call: Call<GetEduContentsResponse>, response: Response<GetEduContentsResponse>) {
                if(response.isSuccessful){
                    Observable.just(response.body()!!.data.content)
                            .subscribe { contents -> ContentsObject.contentsEduFragmentPresenter.responseList(contents) }
                }
            }
        })
    }

    fun requestSenseList(){
        contentsNetworkService.getEduContentsList(ApplicationData.auth).enqueue(object : Callback<GetEduContentsResponse>{
            override fun onFailure(call: Call<GetEduContentsResponse>, t: Throwable) {
                Log.v("ConetnstList", t.toString())
            }

            override fun onResponse(call: Call<GetEduContentsResponse>, response: Response<GetEduContentsResponse>) {
                if(response.isSuccessful){
                    Observable.just(response.body()!!.data.content)
                            .subscribe { contents -> ContentsObject.contentsEduFragmentPresenter.responseList(contents) }
                }
            }
        })
    }

}