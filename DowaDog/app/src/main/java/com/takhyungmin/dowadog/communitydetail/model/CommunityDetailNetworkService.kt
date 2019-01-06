package com.takhyungmin.dowadog.communitydetail.model

import com.google.gson.JsonObject
import com.takhyungmin.dowadog.communitydetail.model.delete.DeleteCommunityCommentResponse
import com.takhyungmin.dowadog.communitydetail.model.delete.DeleteCommunityDetailPostResponse
import com.takhyungmin.dowadog.communitydetail.model.get.GetCommunityCommentResponse
import com.takhyungmin.dowadog.communitydetail.model.get.GetCommunityPostDetailResponse
import com.takhyungmin.dowadog.communitydetail.model.post.PostCommunityCommentWriteResponse
import retrofit2.Call
import retrofit2.http.*

interface CommunityDetailNetworkService {


    // 커뮤니티 포스트 디테일 보기
    @GET("api/normal/community/{communityID}")
    fun GetCommunityPostDetailResponse(
            @Header("Authorization") authorization: String,
            @Header("Content-Type") content_type: String,
            @Path("communityID") communityID: Int
    ): Call<GetCommunityPostDetailResponse>

    // 커뮤니티 글 삭제
    @DELETE("api/normal/community/{communityId}")
    fun deleteCommunityPostResponse(
            @Header("Authorization") authorization: String,
            @Path("communityId") communityId: Int
    ): Call<DeleteCommunityDetailPostResponse>

    // 댓글 가져오기
    @GET("api/normal/community/{communityId}/comments")
    fun getCommunityCommentResponse(
            @Header("Authorization") authorization: String,
            @Path("communityId") communityId: Int
    ): Call<GetCommunityCommentResponse>

    // 댓글 쓰기
    @POST("api/normal/community/{communityId}/comments")
    fun postCommunityCommentWriteResponse(
            @Header("Authorization") authorization: String,
            @Header("Content-Type") content_type: String,
            @Path("communityId") communityId: Int,
            @Body() body : JsonObject
    ): Call<PostCommunityCommentWriteResponse>

    // 댓글 삭제
    @DELETE("api/normal/community/comments/{commentId}")
    fun deleteCommunityCommentResponse(
            @Header("Authorization") authorization: String,
            @Header("Content-Type") content_type: String,
            @Path("commentId") commentId: Int
    ): Call<DeleteCommunityCommentResponse>
}