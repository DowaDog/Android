package com.takhyungmin.dowadog.signup.model

import com.takhyungmin.dowadog.home.model.get.GetDuplicateResponse
import com.takhyungmin.dowadog.signup.model.get.GetSignInfoEmailResponse
import com.takhyungmin.dowadog.signup.model.post.PostSignIdSettingResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface SignUpNetworkService {

    //이메일 중복체크
    @GET("api/signup/duplicateEmail")
    fun GetSignInfoEmailResponse(
            @Query("email") email : String
    ) : Call<GetSignInfoEmailResponse>

    //회원가입
    @Multipart
    @POST("api/signup")
    fun postSignIdSettingResponse(

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

    //아이디 중복체크
    @GET("api/signup/duplicateId")
    fun checkDuplicate(@Query("id") id: String): Call<GetDuplicateResponse>

}