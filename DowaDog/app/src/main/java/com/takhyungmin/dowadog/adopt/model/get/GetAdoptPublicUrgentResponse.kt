package com.takhyungmin.dowadog.adopt.model.get

data class GetAdoptPublicUrgentResponse (
        var status : Int,
        var message : String,
        var data : GetAdoptPublicUrgentData
)