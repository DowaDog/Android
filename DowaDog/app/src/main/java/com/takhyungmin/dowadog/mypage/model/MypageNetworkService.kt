package com.takhyungmin.dowadog.mypage.model

import com.takhyungmin.dowadog.mypage.model.get.GETMypageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface MypageNetworkService {
    //마이페이지 정보 가져오기
    @GET("api/normal/mypage")
    fun getMypageList(
            @Header("Authorization") authorization: String
    ): Call<GETMypageResponse>

}


