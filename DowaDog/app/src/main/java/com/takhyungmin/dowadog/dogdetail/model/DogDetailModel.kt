package com.takhyungmin.dowadog.dogdetail.model

import android.util.Log
import com.takhyungmin.dowadog.dogdetail.model.get.GetDogDetailResponse
import com.takhyungmin.dowadog.utils.ApplicationData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DogDetailModel {
    private var dogDetailNetworkService: DogDetailNetworkService

    init {
        val builder = Retrofit.Builder()
        val retrofit = builder
                .baseUrl(ApplicationData.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        dogDetailNetworkService = retrofit.create(DogDetailNetworkService::class.java)
    }

    fun getDogDetailData(animalId: Int) {

        dogDetailNetworkService.GetDogDetailResponse(ApplicationData.auth, animalId).enqueue(object : Callback<GetDogDetailResponse> {
            override fun onFailure(call: Call<GetDogDetailResponse>?, t: Throwable?) {
                Log.e("getDogDetailData 통신 실패", t.toString())
            }

            override fun onResponse(call: Call<GetDogDetailResponse>?, response: Response<GetDogDetailResponse>?) {
                response?.takeIf { it.isSuccessful }
                        ?.body()
                        ?.let {
                            DogDetailObject.dogDetailActivityPresenter.responseData(it)
                        }
            }
        })
    }
}