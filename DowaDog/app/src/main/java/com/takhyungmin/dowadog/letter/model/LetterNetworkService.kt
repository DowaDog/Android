package com.takhyungmin.dowadog.letter.model

import com.takhyungmin.dowadog.letter.model.get.GETLetterActivityResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface LetterNetworkService {

    //우체통 정보 가져오기
    @GET("api/normal/mypage/mailboxes")
    fun getLetterList(
            @Header("Authorization") authorization: String
    ): Call<GETLetterActivityResponse>
}