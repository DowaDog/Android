package com.takhyungmin.dowadog.contents.model.get

data class GetEduContentsContents (
        var id : Int,
        var title : String,
        var subtitle : String,
        var type : String,
        var imgPath : String,
        var educated : Boolean,
        var auth : Boolean
)
