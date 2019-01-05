package com.takhyungmin.dowadog.login.model.get

data class GetAccessToken (
        var data : String,
        var now : Long,
        var expiredAt : Long
)