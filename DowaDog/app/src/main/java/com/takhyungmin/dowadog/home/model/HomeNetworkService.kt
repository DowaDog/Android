package com.takhyungmin.dowadog.home.model

import com.takhyungmin.dowadog.home.model.get.GetDuplicateResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeNetworkService {

    //아이디 중복체크
    @GET("signup/duplicateId")
    fun checkDuplicate(@Query("id") id: String): Call<GetDuplicateResponse>

}