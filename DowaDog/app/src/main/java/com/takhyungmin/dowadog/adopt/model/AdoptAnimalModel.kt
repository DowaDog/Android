package com.takhyungmin.dowadog.adopt.model

import android.util.Log
import com.takhyungmin.dowadog.adopt.AdoptObject
import com.takhyungmin.dowadog.adopt.model.get.GetAdoptPublicDetailResponse
import com.takhyungmin.dowadog.adopt.model.get.GetAdoptPublicUrgentContents
import com.takhyungmin.dowadog.adopt.model.get.GetAdoptPublicUrgentResponse
import com.takhyungmin.dowadog.adopt.model.get.UrgentAnimalData
import com.takhyungmin.dowadog.utils.ApplicationData
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*


class AdoptAnimalModel {
    private var adoptNetworkService : AdoptNetworkService
    private lateinit var urgentAnimalData: ArrayList<UrgentAnimalData>

    init{
        val builder = Retrofit.Builder()
        val retrofit = builder
                .baseUrl(ApplicationData.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        adoptNetworkService = retrofit.create(AdoptNetworkService::class.java)
    }

    fun getAdoptUrgentPublicList(page : Int, limit : Int){
        adoptNetworkService.getCommunityList(page, limit).enqueue(object : Callback<GetAdoptPublicUrgentResponse>{
            override fun onFailure(call: Call<GetAdoptPublicUrgentResponse>, t: Throwable) {
                Log.v("AdoptUrgent", t.toString())
            }

            override fun onResponse(call: Call<GetAdoptPublicUrgentResponse>, response: Response<GetAdoptPublicUrgentResponse>) {
                if(response.isSuccessful){
                    Log.v("AdoptUrgent", "success")
                    Observable.just(response.body()!!.data.content)
                            .subscribe { contents -> manufactureContents(page, contents) }
                }else{
                    Log.v("AdoptUrgent", "fail")
                }
            }

        })
    }

    val manufactureContents = {page : Int, contents : ArrayList<GetAdoptPublicUrgentContents> ->

        val now = System.currentTimeMillis()
        val date = Date(now)
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
        val beginDate = sdf.format(date)
        val today = sdf.parse(beginDate)
        urgentAnimalData = ArrayList()

        contents.forEach { it ->
            val endDate = sdf.parse(it.noticeEddt)
            val diff = endDate.time - today.time
            val dDay = diff / (24 * 60 * 60 * 1000)
            if(it.kindCd == null)
                it.kindCd = ""
            urgentAnimalData.add(UrgentAnimalData("D-" + dDay.toString(), it.thumbnailImg, it.kindCd!!, "["+it.region+"] "))
        }

        Observable.just(urgentAnimalData)
                .subscribe { it ->
                    if(page == 0){
                        AdoptObject.adoptUrgentAnimalActivityPresenter.firstResponseUrgentList(it)
                    } else{
                        AdoptObject.adoptUrgentAnimalActivityPresenter.responseUrgentList(it)
                    }}
    }

    fun getAdoptDetail(id : Int){
        adoptNetworkService.getAdoptDetail(id).enqueue(object : Callback<GetAdoptPublicDetailResponse>{
            override fun onFailure(call: Call<GetAdoptPublicDetailResponse>, t: Throwable) {
                Log.v("AdoptUrgent", t.toString())
            }

            override fun onResponse(call: Call<GetAdoptPublicDetailResponse>, response: Response<GetAdoptPublicDetailResponse>) {
                if(response.isSuccessful){
                    Log.v("AdoptUrgent", "success")
                    Observable.just(response.body()!!.data)
                            .subscribe { detail -> AdoptObject.adoptUrgentAnimalActivityPresenter.toDetail(detail) }
                }else{
                    Log.v("AdoptUrgent", "fail")
                }
            }

        })
    }

}