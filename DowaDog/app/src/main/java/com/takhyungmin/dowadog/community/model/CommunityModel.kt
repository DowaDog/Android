package com.takhyungmin.dowadog.community.model

import android.util.Log
import com.takhyungmin.dowadog.community.CommunityObject
import com.takhyungmin.dowadog.community.model.get.GetCommunityResponse
import com.takhyungmin.dowadog.utils.ApplicationData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CommunityModel {
    private var communityNetworkService : CommunityNetworkService

    init{
        val builder = Retrofit.Builder()
        val retrofit = builder
                .baseUrl(ApplicationData.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        communityNetworkService = retrofit.create(CommunityNetworkService::class.java)
    }


    fun getDuplicateData(page : Int, limit : Int){
        communityNetworkService.getCommunityList(ApplicationData.auth, page, limit).enqueue(object : Callback<GetCommunityResponse> {
            override fun onFailure(call: Call<GetCommunityResponse>, t: Throwable) {
                Log.v("fail", t.toString())
            }

            override fun onResponse(call: Call<GetCommunityResponse>, response: Response<GetCommunityResponse>) {
                if(response.isSuccessful){
                    Log.v("community_mode", page.toString())
                    when(page){
                        0 -> CommunityObject.communityFragmentPresenter.responseCommunityFirstList(response.body()!!.data.content)
                        else -> CommunityObject.communityFragmentPresenter.responseCommunityList(response.body()!!.data.content)
                    }
                    //CommunityObject.communityFragmentPresenter()
                }else{
                    Log.v("community_mode", "fail")
                }
            }
        })
    }
}