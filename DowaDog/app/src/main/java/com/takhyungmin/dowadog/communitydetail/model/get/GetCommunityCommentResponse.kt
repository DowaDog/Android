package com.takhyungmin.dowadog.communitydetail.model.get

data class GetCommunityCommentResponse(
        val data: ArrayList<CommunityCommentData>,
        val message: String,
        val status: Int
)