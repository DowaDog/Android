package com.takhyungmin.dowadog.contents.model

import com.takhyungmin.dowadog.contents.model.get.GETContentsEduDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ContentsEduDetailNetworkService {

    //컨텐츠 교육 카드뉴스 상세보기
    @GET("api/normal/cardnews/{cardnewsId}/contents")
fun getContentsEduDetailList(
            @Header("Authorization") authorization: String,
            @Path("cardnewsId") cardnewsId : Int,
            @Query("page") page : Int,
            @Query("limit") limit : Int

    ): Call<GETContentsEduDetailResponse>

}
