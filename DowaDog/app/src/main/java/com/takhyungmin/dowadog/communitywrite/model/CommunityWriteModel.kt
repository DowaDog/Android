package com.takhyungmin.dowadog.communitywrite.model

import android.util.Log
import com.takhyungmin.dowadog.communitywrite.model.post.PostCommunityPostWriteResponse
import com.takhyungmin.dowadog.utils.ApplicationData
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CommunityWriteModel {
    private var communityWriteNetworkService: CommunityWriteNetworkService

    init {
        val builder = Retrofit.Builder()
        val retrofit = builder
                .baseUrl(ApplicationData.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        communityWriteNetworkService = retrofit.create(CommunityWriteNetworkService::class.java)
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

    fun getCommunityPostDetailData(input_title: String, input_contents: String, communityImgFiles: ArrayList<MultipartBody.Part>) {





        if (input_title.isNotEmpty() && input_contents.isNotEmpty()) {

            Log.v("잘돼", input_title)
            Log.v("잘돼", input_contents)


            var title = RequestBody.create(MediaType.parse("text/plain"), input_title)
            var contents = RequestBody.create(MediaType.parse("text/plain"), input_contents)

            Log.v("잘돼", "눌림1003")
            communityWriteNetworkService.postSignUpResponse(ApplicationData.auth
                    , title, contents, communityImgFiles).enqueue(object : Callback<PostCommunityPostWriteResponse> {
                override fun onFailure(call: Call<PostCommunityPostWriteResponse>?, t: Throwable?) {
                    Log.e("커뮤니티 글쓰기 실패", t.toString())
                }

                override fun onResponse(call: Call<PostCommunityPostWriteResponse>?, response: Response<PostCommunityPostWriteResponse>?) {

                    Log.v("잘돼", title.toString())
                    Log.v("잘돼", contents.toString())
                    Log.v("잘돼", "눌림10020")
                    Log.v("잘돼", response.toString())
                    Log.v("잘돼", response!!.body().toString())

                    response?.takeIf { it.isSuccessful }
                            ?.body()
                            ?.let {
                                CommunityWriteObject.communityWriteActivityPreseneter.responseData(it)
                            }
                }
            })


        }
    }
}