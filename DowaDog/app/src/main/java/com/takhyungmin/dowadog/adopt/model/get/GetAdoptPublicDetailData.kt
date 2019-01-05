package com.takhyungmin.dowadog.adopt.model.get

data class GetAdoptPublicDetailData (
        var id : Int,
        var noticeNo : String,
        var type : String,
        var processState : String,
        var sexCd : String,
        var noticeStdt : String,
        var noticeEddt : String,
        var region : String,
        var specialMark : String,
        var remainDateState : Boolean,
        var kindCd : String,
        var age : String,
        var weight : String,
        var thumbnailImg : String,
        var happenPlace : String,
        var careName : String,
        var careTel : String
)