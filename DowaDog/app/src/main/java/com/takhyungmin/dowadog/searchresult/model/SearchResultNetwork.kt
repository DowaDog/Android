package com.takhyungmin.dowadog.searchresult.model

import com.takhyungmin.dowadog.searchresult.model.ggg.ccc
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchResultNetwork {

    // 공공데이터 조건별 검색
    @GET("openapi/animals")
    fun getSearchResultFilterResponse(
            @Header("Authorization") authorization : String,
            @Query("type") type : String?,
            @Query("region") region : String?,
            @Query("remainNoticeDate") remainNoticeDate : Int?,
            @Query("page") page : Int?,
            @Query("limit") limit : Int?,
            @Query("searchWord") searchWord : String?
    ) : Call<ccc>
}