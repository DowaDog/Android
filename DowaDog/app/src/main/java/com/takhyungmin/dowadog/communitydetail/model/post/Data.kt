package com.takhyungmin.dowadog.communitydetail.model.post

data class PostCommunityCommentWriteData(
        val createdAt: String,
        val detail: String,
        val id: Int,
        val today: Boolean,
        val updatedAt: String,
        val userId: String
)