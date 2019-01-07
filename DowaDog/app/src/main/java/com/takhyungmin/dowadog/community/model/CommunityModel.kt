package com.takhyungmin.dowadog.community.model

import android.util.Log
import com.takhyungmin.dowadog.community.CommunityObject
import com.takhyungmin.dowadog.community.model.get.GetCommunityContents
import com.takhyungmin.dowadog.community.model.get.GetCommunityResponse
import com.takhyungmin.dowadog.utils.ApplicationData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class CommunityModel {
    private var communityNetworkService : CommunityNetworkService

    init{
        val builder = Retrofit.Builder()
        val retrofit = builder
                .baseUrl(ApplicationData.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        communityNetworkService = retrofit.create(CommunityNetworkService::class.java)
    }


    fun getDuplicateData(page : Int, limit : Int){
        communityNetworkService.getCommunityList(ApplicationData.auth, page, limit).enqueue(object : Callback<GetCommunityResponse> {
            override fun onFailure(call: Call<GetCommunityResponse>, t: Throwable) {
                Log.v("fail", t.toString())
            }

            override fun onResponse(call: Call<GetCommunityResponse>, response: Response<GetCommunityResponse>) {
                if(response.isSuccessful){
                    Log.v("community_mode", page.toString())
                    calcDate(response.body()!!.data.content)
                    when(page){
                        0 -> CommunityObject.communityFragmentPresenter.responseCommunityFirstList(response.body()!!.data.content)
                        else -> CommunityObject.communityFragmentPresenter.responseCommunityList(response.body()!!.data.content)
                    }
                    //CommunityObject.communityFragmentPresenter()
                }else{
                    Log.v("community_mode", "fail")
                }
            }
        })
    }

    val calcDate = {items : ArrayList<GetCommunityContents> ->
        val now = System.currentTimeMillis()
        val date = Date(now)
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
        val beginDate = sdf.format(date)
        val today = sdf.parse(beginDate)
        //urgentAnimalData = ArrayList()
        Log.v("알려줘", items.toString())


//        items.forEach { it ->
//            val ymd = it.
//            val time = it.updatedAt.split("T")[1].split(":")[0]
//
//
//            Log.v("ymd", ymd)
//            Log.v("time", time)
//
//            val endDate = sdf.parse(it.updatedAt)
//            val diff = endDate.time - today.time
//            val dDay = diff / (24 * 60 * 60 * 1000)
////            if(it.kindCd == null)
////                it.kindCd = ""
////            urgentAnimalData.add(UrgentAnimalData(it.id, "D-" + dDay.toString(), it.thumbnailImg, it.kindCd!!, it.sexCd, "["+it.region+"] "))
//        }
    }
}