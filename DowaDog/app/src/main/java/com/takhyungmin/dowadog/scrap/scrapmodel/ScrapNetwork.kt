package com.takhyungmin.dowadog.scrap.scrapmodel

import com.takhyungmin.dowadog.scrap.scrapmodel.get.GetMyScrapResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ScrapNetwork {
    // 스크랩 조회
    @GET("api/normal/mypage/scraps")
    fun getScrapList(
            @Header("Authorization") authorization: String)
            : Call<GetMyScrapResponse>
}