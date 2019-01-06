package com.takhyungmin.dowadog.scrap.scrapmodel.get

data class GetMyScrapResponse(
        val data: ArrayList<GetMyScrapData>,
        val message: String,
        val status: Int
)