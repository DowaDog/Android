package com.takhyungmin.dowadog.login.model

import com.takhyungmin.dowadog.login.model.get.GetLoginResponse
import com.takhyungmin.dowadog.login.model.post.PostLoginDTO
import com.takhyungmin.dowadog.login.model.post.PostRefreshResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginNetworkService {
    //로그인
    @POST("api/auth/login")
    fun postLogin(
            @Body postLoginDTO: PostLoginDTO
    ) : Call<GetLoginResponse>

    //갱신
    @POST("api/auth/refresh")
    fun postRefresh(@Header("Authorization") token : String) : Call<PostRefreshResponse>

}