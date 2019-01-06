package com.takhyungmin.dowadog.home.model.get

data class GetUserInfoResponse (
        var status : Int,
        var message : String,
        var data : GetUserInfoData
)