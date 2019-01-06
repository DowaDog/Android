package com.takhyungmin.dowadog.contents.model

import com.takhyungmin.dowadog.contents.model.get.GetEduContentsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ContentsNetworkService {
    //컨텐츠 교육 리스트
    @GET("api/normal/cardnews/education")
    fun getEduContentsList(
            @Header("Authorization") auth : String
    ) : Call<GetEduContentsResponse>
}