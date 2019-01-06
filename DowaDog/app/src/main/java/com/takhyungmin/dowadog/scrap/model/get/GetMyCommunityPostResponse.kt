package com.takhyungmin.dowadog.scrap.model.get

data class GetMyCommunityPostResponse(
        val data: ArrayList<GetMyCommunityPostData>,
        val message: String,
        val status: Int
)