package com.takhyungmin.dowadog.community.model

import com.takhyungmin.dowadog.community.model.get.GetCommunityResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CommunityNetworkService {
    //커뮤니티 메인 리스트
    @GET("normal/community")
    fun getCommunityList(@Header("Authorization") Authorization : String,
                         @Query("page") page : Int,
                         @Query("limit") limit : Int): Call<GetCommunityResponse>

}