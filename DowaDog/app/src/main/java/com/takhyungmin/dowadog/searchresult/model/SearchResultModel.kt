package com.takhyungmin.dowadog.searchresult.model

import android.util.Log
import com.takhyungmin.dowadog.searchresult.SearchResultObject
import com.takhyungmin.dowadog.searchresult.model.ggg.ccc
import com.takhyungmin.dowadog.utils.ApplicationData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchResultModel {
    private var searchResultNetwork: SearchResultNetwork

    init {
        val builder = Retrofit.Builder()
        val retrofit = builder
                .baseUrl(ApplicationData.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        searchResultNetwork = retrofit.create(SearchResultNetwork::class.java)
    }

    fun getSearchResulData(type: String, region: String, remainNoticeDate: Int, page: Int, limit: Int, searchKeyword : String) {

        searchResultNetwork.getSearchResultFilterResponse(ApplicationData.auth,
                type, region, remainNoticeDate, page, limit, searchKeyword).enqueue(object : Callback<ccc> {
            override fun onFailure(call: Call<ccc>?, t: Throwable?) {
                Log.e("SearchResultData 통신 실패", t.toString())
            }

            override fun onResponse(call: Call<ccc>?, response: Response<ccc>?) {

                response?.takeIf { it.isSuccessful }
                        ?.body()
                        ?.let {
                            when(page){
                                0 -> {
                                    Log.v("TAGGG234", "type :" + type + " egion :" + region + " remainNoticeDate :" +  remainNoticeDate + " page :" + page + " limit :" + limit)
                                    SearchResultObject.searchResultActivityPresenter.requestData(it)
                                }
                                else -> {
                                    Log.v("TAGGG23410101010", "type :" + type + " egion :" + region + " remainNoticeDate :" +  remainNoticeDate + " page :" + page + " limit :" + limit)
                                    SearchResultObject.searchResultActivityPresenter.responseMoreSearchResultList(it.data.content)
                                }
                            }

                            // 서버에서 보내기 직전 데이터 보기
                            // 데이터 자료형이 틀린지 확인하기
                        }
            }
        })
    }

    fun getKeywordSearchResulData(type: String, region: String, remainNoticeDate: Int, page: Int, limit: Int, searchKeyword : String) {

        searchResultNetwork.getSearchResultFilterResponse(ApplicationData.auth,
                type, region, remainNoticeDate, page, limit, searchKeyword).enqueue(object : Callback<ccc> {
            override fun onFailure(call: Call<ccc>?, t: Throwable?) {
                Log.e("SearchResultData 통신 실패", t.toString())
            }

            override fun onResponse(call: Call<ccc>?, response: Response<ccc>?) {

                response?.takeIf { it.isSuccessful }
                        ?.body()
                        ?.let {
                            when(page){
                                0 -> {
                                    SearchResultObject.searchKeywordResultActivityPresenter.requestData(it)
                                }
                                else -> {
                                    SearchResultObject.searchKeywordResultActivityPresenter.responseMoreSearchResultList(it.data.content)
                                }
                            }

                            // 서버에서 보내기 직전 데이터 보기
                            // 데이터 자료형이 틀린지 확인하기
                        }
            }
        })
    }
}