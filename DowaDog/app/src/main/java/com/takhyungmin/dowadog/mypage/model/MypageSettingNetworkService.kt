package com.takhyungmin.dowadog.mypage.model

import com.takhyungmin.dowadog.mypage.model.get.GETMypageSettingResponse
import com.takhyungmin.dowadog.mypage.model.put.PUTMypageSettingResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface MypageSettingNetworkService {

    //마이페이지 수정
    @Multipart
    @PUT("api/normal/mypage")
    fun putMypageList(
            @Header("Authorization") authorization: String,
            @Part("name") name: RequestBody,
            @Part("phone") phone: RequestBody,
            @Part("email") email : RequestBody,
            @Part("birth") birth: RequestBody,
            @Part profileImgFile: MultipartBody.Part?
    ): Call<PUTMypageSettingResponse>

    @GET("api/normal/mypage/myinfo")
    fun getMypagesetting(
            @Header("Authorization") authorization: String
    ): Call<GETMypageSettingResponse>
}