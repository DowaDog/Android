package com.takhyungmin.dowadog.communitydetail.model

import android.util.Log
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.takhyungmin.dowadog.communitydetail.CommunityDetailObject
import com.takhyungmin.dowadog.communitydetail.model.delete.DeleteCommunityCommentResponse
import com.takhyungmin.dowadog.communitydetail.model.delete.DeleteCommunityDetailPostResponse
import com.takhyungmin.dowadog.communitydetail.model.get.GetCommunityCommentResponse
import com.takhyungmin.dowadog.communitydetail.model.get.GetCommunityPostDetailResponse
import com.takhyungmin.dowadog.communitydetail.model.post.PostCommunityCommentWriteResponse
import com.takhyungmin.dowadog.utils.ApplicationData
import com.takhyungmin.dowadog.utils.SharedPreferenceController
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CommunityDetailModel {
    private var communityDetailNetworkService: CommunityDetailNetworkService

    init {
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

    fun getCommunityPostDetailData(communityId: Int) {
        Log.v("TAGGG",communityId.toString())
        communityDetailNetworkService.GetCommunityPostDetailResponse(SharedPreferenceController.getAccessToken(ApplicationData.applicationContext),
                "application/json", communityId).enqueue(object : Callback<GetCommunityPostDetailResponse> {
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

    fun deleteCommunityPostDetailData(communityId: Int) {
        communityDetailNetworkService.deleteCommunityPostResponse(ApplicationData.auth,
                communityId).enqueue(object : Callback<DeleteCommunityDetailPostResponse> {
            override fun onFailure(call: Call<DeleteCommunityDetailPostResponse>?, t: Throwable?) {
                Log.e("커뮤니티 디테일 모델 통신 실패", t.toString())
            }

            override fun onResponse(call: Call<DeleteCommunityDetailPostResponse>?, response: Response<DeleteCommunityDetailPostResponse>?) {
                Log.e("TAGG", response.toString())
                Log.e("TAGG", response!!.body().toString())
                response?.takeIf { it.isSuccessful }
                        ?.body()
                        ?.let {
                            CommunityDetailObject.communityDetailActivityPresenter.responseDeleteData(it)
                        }
            }
        })
    }

    fun getCommunityCommentData(communityId: Int) {
        communityDetailNetworkService.getCommunityCommentResponse(SharedPreferenceController.getAccessToken(ApplicationData.applicationContext), communityId).enqueue(object : Callback<GetCommunityCommentResponse> {
            override fun onFailure(call: Call<GetCommunityCommentResponse>?, t: Throwable?) {
                Log.e("커뮤니티 디테일 댓글 모델 통신 실패", t.toString())
            }

            override fun onResponse(call: Call<GetCommunityCommentResponse>?, response: Response<GetCommunityCommentResponse>?) {
                Log.e("TAGG", response.toString())
                response?.takeIf { it.isSuccessful }
                        ?.body()
                        ?.let {
                            CommunityDetailObject.communityDetailActivityPresenter.responseCommnetData(it)
                        }
            }
        })
    }

    fun postCommunityCommentWriteRequest(communityId: Int, description: String) {
        //Json 형식의 객체 만들기
        Log.e("TAGG", "진입")
        var jsonObject = JSONObject()
        jsonObject.put("detail", description)
        //Gson 라이브러리의 Json Parser을 통해 객체를 Json으로!
        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject
        communityDetailNetworkService.postCommunityCommentWriteResponse(SharedPreferenceController.getAccessToken(ApplicationData.applicationContext), "application/json", communityId, gsonObject)
                .enqueue(object: Callback<PostCommunityCommentWriteResponse>{
                    override fun onFailure(call: Call<PostCommunityCommentWriteResponse>?, t: Throwable?) {
                        Log.e("커뮤니티 디테일 댓글 쓰기 통신 실패", t.toString())
                    }

                    override fun onResponse(call: Call<PostCommunityCommentWriteResponse>?, response: Response<PostCommunityCommentWriteResponse>?) {
                        Log.e("TAGG", response.toString())
                        response?.takeIf { it.isSuccessful }
                                ?.body()
                                ?.let {
                                    CommunityDetailObject.communityDetailActivityPresenter.responseCommentWriteData(it)
                                }
                    }
                })
    }

    fun deleteCommunityCommentWriteRequest(commentId: Int) {
        communityDetailNetworkService.deleteCommunityCommentResponse(SharedPreferenceController.getAccessToken(ApplicationData.applicationContext), "application/json", commentId)
                .enqueue(object : Callback<DeleteCommunityCommentResponse>{
                    override fun onFailure(call: Call<DeleteCommunityCommentResponse>?, t: Throwable?) {
                        Log.e("커뮤니티 디테일 댓글 삭제 통신 실패", t.toString())
                    }

                    override fun onResponse(call: Call<DeleteCommunityCommentResponse>?, response: Response<DeleteCommunityCommentResponse>?) {
                        Log.e("TAGG", response.toString())
                        response?.takeIf { it.isSuccessful }
                                ?.body()
                                ?.let {
                                    CommunityDetailObject.communityDetailActivityPresenter.responseCommentDeleteData(it)
                                }
                    }
                })
    }
}