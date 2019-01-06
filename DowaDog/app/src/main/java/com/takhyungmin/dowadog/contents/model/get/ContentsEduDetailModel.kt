package com.takhyungmin.dowadog.contents.model.get

import android.util.Log
import com.takhyungmin.dowadog.contents.model.ContentEduDetailObject
import com.takhyungmin.dowadog.contents.model.ContentsEduDetailNetworkService
import com.takhyungmin.dowadog.letter.model.LetterNetworkService
import com.takhyungmin.dowadog.letter.model.LetterObject
import com.takhyungmin.dowadog.letter.model.get.GETLetterActivityResponse
import com.takhyungmin.dowadog.utils.ApplicationData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ContentsEduDetailModel {
    private var contentsEduDetailNetworkService: ContentsEduDetailNetworkService


    init {
        val builder = Retrofit.Builder()
        val retrofit = builder
                .baseUrl(ApplicationData.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        contentsEduDetailNetworkService = retrofit.create(ContentsEduDetailNetworkService::class.java)
    }

    fun getContentsEduDetailData(id : Int) {

        contentsEduDetailNetworkService.getContentsEduDetailList("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoidGFla3l1bmcwNDAyIiwiaXNzIjoiZG93YWRvZyIsImV4cCI6MTU3ODIyNjg0MX0.abzE4hLsRbVe5Xj-PigEC1SlUNwbcaYZfNRu0V4nsU0",
                id,10,0)
                .enqueue(object : Callback<GETContentsEduDetailResponse> {

                    override fun onFailure(call: Call<GETContentsEduDetailResponse>?, t: Throwable?) {
                        Log.e("eduDetail통신 실패", t.toString())
                    }

                    override fun onResponse(call: Call<GETContentsEduDetailResponse>?, response: Response<GETContentsEduDetailResponse>?) {

                        response?.takeIf { it.isSuccessful }
                                ?.body()
                                ?.let {
                                    Log.v("message", it.message)
                                    ContentEduDetailObject.contentsEduDetailActivityPresenter.responseData(it)
                                    //Log.v("TAGG","모델 리스폰스")

                                }
                    }
                })
    }

}