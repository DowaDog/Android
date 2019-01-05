package com.takhyungmin.dowadog.dogdetail.model

import com.takhyungmin.dowadog.dogdetail.model.get.GetDogDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DogDetailNetworkService {

    // 커뮤니티 포스트 디테일 보기
    @GET("openapi/animals/{animalId}")
    fun GetDogDetailResponse(
            @Path("animalId") animalId: Int
    ): Call<GetDogDetailResponse>
}