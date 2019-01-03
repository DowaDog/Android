package com.takhyungmin.dowadog.communitydetail.model.get

data class GetCommunityPostDetailData(
        val auth: Boolean,
        val communityImgList: ArrayList<CommunityImg>,
        val communityThumbnailImg: String,
        val createdAt: String,
        val detail: String,
        val id: Int,
        val title: String,
        val today: Boolean,
        val updatedAt: String,
        val userId: String,
        val userProfileImg: String
)