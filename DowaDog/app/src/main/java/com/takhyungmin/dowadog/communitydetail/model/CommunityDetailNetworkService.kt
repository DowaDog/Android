package com.takhyungmin.dowadog.communitydetail.model

import com.takhyungmin.dowadog.communitydetail.model.get.GetCommunityPostDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface CommunityDetailNetworkService {


    // 커뮤니티 포스트 디테일 보기
    @GET("normal/community/{communityID}")
    fun GetCommunityPostDetailResponse(
        @Header("Authorization") authorization: String,
        @Header("Content-Type") content_type: String,
        @Path("communityID") communityID: Int
    ): Call<GetCommunityPostDetailResponse>
}