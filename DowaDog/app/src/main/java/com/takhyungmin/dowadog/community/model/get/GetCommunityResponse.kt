package com.takhyungmin.dowadog.community.model.get

data class GetCommunityResponse (
        var status : Int,
        var message : String,
        var data : GetCommunityData
)