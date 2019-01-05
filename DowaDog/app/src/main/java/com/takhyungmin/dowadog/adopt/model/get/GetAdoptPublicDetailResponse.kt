package com.takhyungmin.dowadog.adopt.model.get

data class GetAdoptPublicDetailResponse (
        var status : String,
        var message : String,
        var data : GetAdoptPublicDetailData
)