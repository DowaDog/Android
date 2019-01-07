package com.takhyungmin.dowadog.contents.model.get

import android.util.Log
import com.takhyungmin.dowadog.contents.model.ContentEduDetailObject
import com.takhyungmin.dowadog.contents.model.ContentsEduDetailNetworkService
import com.takhyungmin.dowadog.contents.model.ContentsSenseDetailObject
import com.takhyungmin.dowadog.contents.model.post.PostScrapResponse
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

    fun postEduContentsScrap(id : Int){
        contentsEduDetailNetworkService.postScrapEduContents(ApplicationData.auth, id).enqueue(object : Callback<PostScrapResponse>{
            override fun onFailure(call: Call<PostScrapResponse>, t: Throwable) {
                Log.v("EduContents", t.toString())
            }

            override fun onResponse(call: Call<PostScrapResponse>, response: Response<PostScrapResponse>) {
                if(response.isSuccessful){
                    Log.v("EduContents", response.message())
                    if(response.body()!!.message.contains("추가"))
                        ContentEduDetailObject.contentsEduDetailActivityPresenter.responseScrap(true)
                    else
                        ContentEduDetailObject.contentsEduDetailActivityPresenter.responseScrap(false)
                }else{
                    Log.v("EduContents", "fail")
                }
            }
        })
    }

    fun postEduContentsComplete(id : Int){
        contentsEduDetailNetworkService.postCompleteEduContents(ApplicationData.auth, id).enqueue(object : Callback<Unit>{
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.v("EduContents", t.toString())
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if(response.isSuccessful){
                    ContentEduDetailObject.contentsEduDetailActivityPresenter.responseComplete(true)
                }
            }
        })
    }

    //교육 컨텐츠 상식 디테일
    fun getContentsSenseDetailData(id : Int) {

        contentsEduDetailNetworkService.getContentsSenseDetailList("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoidGFla3l1bmcwNDAyIiwiaXNzIjoiZG93YWRvZyIsImV4cCI6MTU3ODI4NDQzOH0.MTN9ke4pknmiqwu29Je24mUWn56GVM8OEuCca4HEPqI",id)
                .enqueue(object : Callback<ContentSenseDetailResponse> {

                    override fun onFailure(call: Call<ContentSenseDetailResponse>?, t: Throwable?) {

                    }

                    override fun onResponse(call: Call<ContentSenseDetailResponse>?, response: Response<ContentSenseDetailResponse>?) {
                        Log.v("TAGG","모델 리스폰스231123")
                        response?.takeIf { it.isSuccessful }
                                ?.body()
                                ?.let {
                                    Log.v("TAGG", it.message)
                                    ContentsSenseDetailObject.contentsSenseDetailActivityPresenter.responseData(it)
                                    Log.v("TAGG","모델 리스폰스")

                                }
                    }
                })
    }

}