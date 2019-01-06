package com.takhyungmin.dowadog.contents.model

import com.takhyungmin.dowadog.contents.model.get.GetEduContentsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ContentsNetworkService {
    //컨텐츠 교육 리스트
    @GET("api/normal/cardnews/education")
    fun getEduContentsList(
            @Header("Authorization") auth : String
    ) : Call<GetEduContentsResponse>

    //컨텐츠 상식 리스트
    @GET("api/normal/cardnews/knowledge")
    fun getSenseContentsList(
            @Header("Authorization") auth : String,
            @Query("page") page : Int,
            @Query("limit") limit : Int
    ) : Call<GetEduContentsResponse>
}