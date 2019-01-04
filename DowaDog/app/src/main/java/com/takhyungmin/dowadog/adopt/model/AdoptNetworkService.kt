package com.takhyungmin.dowadog.adopt.model

import com.takhyungmin.dowadog.adopt.model.get.GetAdoptPublicUrgentResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AdoptNetworkService {

    //긴급 동물 조회(from 공공)
    @GET("openapi/animals/emergency")
    fun getCommunityList(@Query("page") page : Int,
                         @Query("limit") limit : Int): Call<GetAdoptPublicUrgentResponse>

}