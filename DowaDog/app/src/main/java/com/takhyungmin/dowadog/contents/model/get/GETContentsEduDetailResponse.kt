package com.takhyungmin.dowadog.contents.model.get

import com.takhyungmin.dowadog.contents.model.Data

data class GETContentsEduDetailResponse(
        val data: Data,
        val message: String,
        val status: Int
)