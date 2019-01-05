package com.takhyungmin.dowadog.login.model.get

data class GetLoginResponse (
        var status : String,
        var message : String,
        var data : GetLoginData
)