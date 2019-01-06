package com.takhyungmin.dowadog.login.model.get

data class GetRefreshToken (
        var data : String,
        var now : Long,
        var expiredAt : Long
)