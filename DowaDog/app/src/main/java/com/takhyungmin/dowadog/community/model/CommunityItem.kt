package com.takhyungmin.dowadog.community.model

data class CommunityItem (
        //이미지는 나중에 스트링
        var communityProfile : Int,
        var communityName : String,
        var communityImage : ArrayList<Int>,
        var communityTitle : String,
        var communityTime : String

)