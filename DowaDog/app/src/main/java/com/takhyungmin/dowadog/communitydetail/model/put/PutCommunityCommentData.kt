package com.takhyungmin.dowadog.communitydetail.model.put

data class PutCommunityCommentData(
        val createdAt: String,
        val detail: String,
        val id: Int,
        val today: Boolean,
        val updatedAt: String,
        val userId: String
)