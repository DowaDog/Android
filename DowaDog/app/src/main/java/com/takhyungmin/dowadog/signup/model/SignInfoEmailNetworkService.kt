package com.takhyungmin.dowadog.signup.model

import com.takhyungmin.dowadog.communitydetail.model.get.GetCommunityPostDetailResponse
import com.takhyungmin.dowadog.signup.model.get.GetSignInfoEmailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface SignInfoEmailNetworkService {
    //이메일 중복체크
    @GET("api/signup/duplicateEmail")
    fun GetSignInfoEmailResponse(
            @Query("email") email : String
    ) : Call<GetSignInfoEmailResponse>
}

//
//interface CommunityDetailNetworkService {
//
//    // 커뮤니티 포스트 디테일 보기
//    @GET("normal/community/{communityID}")
//    fun GetCommunityPostDetailResponse(
//            @Header("Authorization") authorization: String,
//            @Header("Content-Type") content_type: String,
//            @Path("communityID") communityID: Int
//    ): Call<GetCommunityPostDetailResponse>
//
//}

//    // Params = Path로 접근
//    @GET("normal/cardnews/knowledge")
//    fun GetCommunityPostDetailResponse2(
//            @Header("Authorization") authorization: String,
//            @Header("Content-Type") content_type: String,
//            @Query("page") page: Int,
//            @Query("limit") limit: Int
//    ): Call<GetCommunityPostDetailResponse>