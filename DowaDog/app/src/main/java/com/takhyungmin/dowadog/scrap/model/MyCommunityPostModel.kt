package com.takhyungmin.dowadog.scrap.model

import android.util.Log
import com.takhyungmin.dowadog.scrap.model.get.GetMyCommunityPostResponse
import com.takhyungmin.dowadog.utils.ApplicationData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyCommunityPostModel {
    private var myCommunityPostNetwork: MyCommunityPostNetwork

    init {
        val builder = Retrofit.Builder()
        val retrofit = builder
                .baseUrl(ApplicationData.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        myCommunityPostNetwork = retrofit.create(MyCommunityPostNetwork::class.java)
    }

    fun getMyCommunityPost() {

        myCommunityPostNetwork.getMyCommunityPostList(ApplicationData.auth).enqueue(object : Callback<GetMyCommunityPostResponse>{
            override fun onFailure(call: Call<GetMyCommunityPostResponse>?, t: Throwable?) {
                Log.e("getMyCommunityPost통신실패", t.toString())
            }

            override fun onResponse(call: Call<GetMyCommunityPostResponse>?, response: Response<GetMyCommunityPostResponse>?) {

                Log.v("TAGGGG", response!!.body().toString())
                response?.takeIf { it.isSuccessful }
                        ?.body()
                        ?.let {
                            MyCommunityPostObject.myCommunityPostActivityPresenter.requestMyCommunityPostList(it)
                        }
            }
        })
    }
}