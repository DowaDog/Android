package com.takhyungmin.dowadog.communitydetail.model.put

data class PutCommunityCommentResponse(
        val data: PutCommunityCommentData,
        val message: String,
        val status: Int
)