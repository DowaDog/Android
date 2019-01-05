package com.takhyungmin.dowadog.login.model.post

data class PostRefreshResponse (
        var status : String,
        var message : String,
        var data : PostRefreshData
)