package com.takhyungmin.dowadog.communitywrite.model

import com.takhyungmin.dowadog.communitywrite.model.post.PostCommunityPostWriteResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface CommunityWriteNetworkService {

    // 커뮤니티 글 생성
    @Multipart
    @POST("api/normal/community")
    fun postSignUpResponse(
            @Header("Authorization") authorization: String,
            @Part("title") title: RequestBody,
            @Part("detail") detail: RequestBody,
            @Part communityImgFiles: ArrayList<MultipartBody.Part>
    ) : Call<PostCommunityPostWriteResponse>

//    //회원가입
//    @Multipart
//    @POST("api/signup")
//    fun PostSignIdSettingResponse(
//
//            @Part("id") id : RequestBody,
//            @Part("password") password : RequestBody,
//            @Part("name") name : RequestBody,
//            @Part("birth") birth : RequestBody,
//            @Part("phone") phone : RequestBody,
//            @Part("email") email : RequestBody,
//            @Part("gender") gender : RequestBody,
//            @Part("deviceToken") deviceToken : RequestBody,
//            @Part("type") type : RequestBody,
//            @Part profileImgFile : MultipartBody.Part?,
//            @Part("pushAllow") pushAllow : RequestBody
//
//    ) : Call<PostSignIdSettingResponse>


}