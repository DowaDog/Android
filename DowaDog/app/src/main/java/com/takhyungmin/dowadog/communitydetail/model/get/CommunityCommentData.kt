package com.takhyungmin.dowadog.communitydetail.model.get

data class CommunityCommentData(
        val auth: Boolean,
        val createdAt: String,
        val detail: String,
        val id: Int,
        val today: Boolean,
        val updatedAt: String,
        val userId: String,
        val userProfileImg: String
)