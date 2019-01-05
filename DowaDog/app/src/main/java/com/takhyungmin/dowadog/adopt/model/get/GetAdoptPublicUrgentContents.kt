package com.takhyungmin.dowadog.adopt.model.get

data class GetAdoptPublicUrgentContents (
        var id : Int,
        var type : String,
        var sexCd : String,
        var kindCd: String?,
        var region : String,
        var noticeEddt : String,
        var remainDateState : Boolean,
        var thumbnailImg : String?
)