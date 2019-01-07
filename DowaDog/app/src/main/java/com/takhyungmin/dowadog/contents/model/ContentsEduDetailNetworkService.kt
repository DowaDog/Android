package com.takhyungmin.dowadog.contents.model

import com.takhyungmin.dowadog.contents.model.get.ContentSenseDetailResponse
import com.takhyungmin.dowadog.contents.model.get.GETContentsEduDetailResponse
import com.takhyungmin.dowadog.contents.model.post.PostScrapResponse
import retrofit2.Call
import retrofit2.http.*

interface ContentsEduDetailNetworkService {

    //컨텐츠 교육 카드뉴스 상세보기
    @GET("api/normal/cardnews/{cardnewsId}/contents")
    fun getContentsEduDetailList(
            @Header("Authorization") authorization: String,
            @Path("cardnewsId") cardnewsId: Int,
            @Query("page") page: Int,
            @Query("limit") limit: Int

    ): Call<GETContentsEduDetailResponse>

    @POST("api/normal/cardnews/{cardnewsId}/scrap")
    fun postScrapEduContents(
            @Header("Authorization") authorization: String,
            @Path("cardnewsId") cardnewsId: Int): Call<PostScrapResponse>


    @POST("api/normal/cardnews/{cardnewsId}/complete")
    fun postCompleteEduContents(
            @Header("Authorization") authorization: String,
            @Path("cardnewsId") cardnewsId: Int): Call<Unit>

    //컨텐츠 상식 카드뉴스 상세보기
    @GET("api/normal/cardnews/{cardnewsId}/contents")
    fun getContentsSenseDetailList(
            @Path("cardnewsId") cardnewsId: Int,
            @Query("page") page: Int,
            @Query("limit") limit: Int
    ) : Call<ContentSenseDetailResponse>


}
