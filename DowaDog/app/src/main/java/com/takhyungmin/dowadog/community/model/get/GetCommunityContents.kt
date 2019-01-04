package com.takhyungmin.dowadog.community.model.get

data class GetCommunityContents (
        var id : Int,
        var title : String,
        var detail : String,
        var communityThumbnailImg : String?,
        var communityImgList : ArrayList<GetCommunityList>,
        var today : Boolean,
        var userId : String,
        var userProfileImg : String,
        var createdAt : String,
        var updatedAt : String,
        var auth: Boolean
)