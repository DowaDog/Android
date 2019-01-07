package com.takhyungmin.dowadog.mypage.model.get

data class GETMypageSettingResponse(
        val data: MypageSettingGETData,
        val message: String,
        val status: Int
)