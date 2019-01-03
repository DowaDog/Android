package com.takhyungmin.dowadog.communitydetail.model

import android.util.Log
import com.takhyungmin.dowadog.communitydetail.CommunityDetailObject
import com.takhyungmin.dowadog.communitydetail.model.get.GetCommunityPostDetailResponse
import com.takhyungmin.dowadog.utils.ApplicationData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CommunityDetailModel {
    private var communityDetailNetworkService : CommunityDetailNetworkService

    init{
        val builder = Retrofit.Builder()
        val retrofit = builder
                .baseUrl(ApplicationData.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        communityDetailNetworkService = retrofit.create(CommunityDetailNetworkService::class.java)
    }


    /*fun getCommunityPostDetailData(){
        communityDetailNetworkService.checkDuplicate("aaa").enqueue(object : Callback<GetDuplicateResponse> {
            override fun onFailure(call: Call<GetDuplicateResponse>, t: Throwable) {
                Log.v("fail", t.toString())
            }

            override fun onResponse(call: Call<GetDuplicateResponse>, response: Response<GetDuplicateResponse>) {
                if(response.isSuccessful){
                    HomeObject.homeActivityPresenter.responseData(response.body()!!)
                }
            }
        })
    }*/

    fun getCommunityPostDetailData(){
        communityDetailNetworkService.GetCommunityPostDetailResponse("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoidGFla3l1bmciLCJpc3MiOiJkb3dhZG9nIiwiZXhwIjoxNTc3OTg4NDcyfQ.dZfpU_OPSH6kaVSeumubeDDtmhuhW4w8D_pNUaMn7-U",
                "application/json", 5).enqueue(object: Callback<GetCommunityPostDetailResponse>{
            override fun onFailure(call: Call<GetCommunityPostDetailResponse>?, t: Throwable?) {
                Log.e("CommunityDetail통신실패", t.toString())
            }

            override fun onResponse(call: Call<GetCommunityPostDetailResponse>?, response: Response<GetCommunityPostDetailResponse>?) {

                response?.takeIf { it.isSuccessful }
                        ?.body()
                        ?.let {
                            CommunityDetailObject.communityDetailActivityPresenter.responseData(it)
                        }
            }
        })
    }
}