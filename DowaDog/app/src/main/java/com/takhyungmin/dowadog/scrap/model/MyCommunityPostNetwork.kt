package com.takhyungmin.dowadog.scrap.model

import com.takhyungmin.dowadog.scrap.model.get.GetMyCommunityPostResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface MyCommunityPostNetwork {

    // 내가 쓴글 조회
    @GET("api/normal/mypage/community")
    fun getCommunityList(
            @Header("Authorization") authorization: String)
    :Call<GetMyCommunityPostResponse>
}