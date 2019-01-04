package com.takhyungmin.dowadog.communitywrite.model.post

data class CommunityPostWriteResponse(
        val communityImgList: Any,
        val createdAt: String,
        val detail: String,
        val id: Int,
        val title: String,
        val today: Boolean,
        val updatedAt: String
)