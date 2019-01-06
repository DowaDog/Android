package com.takhyungmin.dowadog.contents.model.get

data class GetEduContentsResponse(
        var status : Int,
        var message : String,
        var data : GetEduContentsData
)