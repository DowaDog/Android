package com.takhyungmin.dowadog.login.model.get

data class GetLoginData(
        var accessToken : GetAccessToken,
        var refreshToken: GetRefreshToken
)