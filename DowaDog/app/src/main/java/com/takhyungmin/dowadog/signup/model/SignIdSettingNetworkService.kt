package com.takhyungmin.dowadog.signup.model

import com.google.gson.JsonObject
import com.takhyungmin.dowadog.signup.model.post.PostSignIdSettingResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface SignIdSettingNetworkService {
    //회원가입
    @Multipart
    @POST("api/signup")
    fun PostSignIdSettingResponse(

            @Part("id") id : RequestBody,
            @Part("password") password : RequestBody,
            @Part("name") name : RequestBody,
            @Part("birth") birth : RequestBody,
            @Part("phone") phone : RequestBody,
            @Part("email") email : RequestBody,
            @Part("gender") gender : RequestBody,
            @Part("deviceToken") deviceToken : RequestBody,
            @Part("type") type : RequestBody,
            @Part profileImgFile : MultipartBody.Part?,
            @Part("pushAllow") pushAllow : RequestBody

            ) : Call<PostSignIdSettingResponse>
}


//    // Params = Path로 접근
//    @GET("normal/cardnews/knowledge")
//    fun GetCommunityPostDetailResponse2(
//            @Header("Authorization") authorization: String,
//            @Header("Content-Type") content_type: String,
//            @Query("page") page: Int,
//            @Query("limit") limit: Int
//    ): Call<GetCommunityPostDetailResponse>


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
