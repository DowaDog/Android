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
    @POST("normal/community")
    fun postSignUpResponse(
            @Header("Authorization") authorization: String,
            @Header("Content-Type") content_type: String,
            @Part("title") title: RequestBody,
            @Part("detail") detail: RequestBody,
            @Part communityImgFiles: ArrayList<MultipartBody.Part>
    ) : Call<PostCommunityPostWriteResponse>


}