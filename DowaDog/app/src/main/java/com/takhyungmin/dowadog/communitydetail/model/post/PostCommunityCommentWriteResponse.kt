package com.takhyungmin.dowadog.communitydetail.model.post

data class PostCommunityCommentWriteResponse(
        val data: PostCommunityCommentWriteData,
        val message: String,
        val status: Int
)