package com.takhyungmin.dowadog.login.model.post

data class PostRefreshData (
        var data : String,
        var now : Long,
        var expiredAt : Long
)