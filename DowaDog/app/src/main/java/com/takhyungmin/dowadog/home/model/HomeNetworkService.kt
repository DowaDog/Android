package com.takhyungmin.dowadog.home.model

import com.takhyungmin.dowadog.home.model.get.GetDuplicateResponse
import com.takhyungmin.dowadog.home.model.get.GetUserInfoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface HomeNetworkService {

    //아이디 중복체크
    @GET("signup/duplicateId")
    fun checkDuplicate(@Query("id") id: String): Call<GetDuplicateResponse>


    //사용자 정보 가져오기
    @GET("api/normal/mypage/myinfo")
    fun getUserInfo(@Header("Authorization") auth : String) : Call<GetUserInfoResponse>


}