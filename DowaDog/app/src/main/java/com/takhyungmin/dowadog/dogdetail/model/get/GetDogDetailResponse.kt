package com.takhyungmin.dowadog.dogdetail.model.get

data class GetDogDetailResponse(
        val data: GetDogDetailData,
        val message: String,
        val status: Int
)