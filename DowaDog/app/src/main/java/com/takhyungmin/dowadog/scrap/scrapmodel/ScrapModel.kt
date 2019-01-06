package com.takhyungmin.dowadog.scrap.scrapmodel

import android.util.Log
import com.takhyungmin.dowadog.scrap.scrapmodel.get.GetMyScrapResponse
import com.takhyungmin.dowadog.utils.ApplicationData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ScrapModel  {
    private var scrapNetwork: ScrapNetwork

    init {
        val builder = Retrofit.Builder()
        val retrofit = builder
                .baseUrl(ApplicationData.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        scrapNetwork = retrofit.create(ScrapNetwork::class.java)
    }

    fun getMyScrapList(){

        scrapNetwork.getScrapList(ApplicationData.auth).enqueue(object: Callback<GetMyScrapResponse>{
            override fun onFailure(call: Call<GetMyScrapResponse>?, t: Throwable?) {
                Log.e("getMyScrapList통신실패", t.toString())
            }

            override fun onResponse(call: Call<GetMyScrapResponse>?, response: Response<GetMyScrapResponse>?) {
                Log.v("TAGGGG", response!!.body().toString())
                response?.takeIf { it.isSuccessful }
                        ?.body()
                        ?.let {
                            ScrapObject.scrapActivityPresenter.responseMyScrapList(it)
                            // MyCommunityPostObject.myCommunityPostActivityPresenter.requestMyCommunityPostList(it)
                        }
            }
        })
    }
}