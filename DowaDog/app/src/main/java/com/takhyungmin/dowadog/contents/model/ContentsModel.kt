package com.takhyungmin.dowadog.contents.model

import com.takhyungmin.dowadog.utils.ApplicationData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ContentsModel {
    private var contentsNetworkService : ContentsNetworkService

    init{
        val builder = Retrofit.Builder()
        val retrofit = builder
                .baseUrl(ApplicationData.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        contentsNetworkService = retrofit.create(ContentsNetworkService::class.java)
    }


}