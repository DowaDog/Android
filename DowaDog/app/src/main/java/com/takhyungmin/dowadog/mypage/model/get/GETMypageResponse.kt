package com.takhyungmin.dowadog.mypage.model.get

import com.takhyungmin.dowadog.mypage.model.Data


data class GETMypageResponse(
        val data: Data,
        val message: String,
        val status: Int
)