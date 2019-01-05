package com.takhyungmin.dowadog.presenter.activity

import com.takhyungmin.dowadog.presenter.BasePresenter
import com.takhyungmin.dowadog.searchresult.SearchResultActivity
import com.takhyungmin.dowadog.searchresult.model.SearchResultModel
import com.takhyungmin.dowadog.searchresult.model.ggg.Content
import com.takhyungmin.dowadog.searchresult.model.ggg.ccc

class SearchResultActivityPresenter: BasePresenter<SearchResultActivity>(){

    val searchResultModel : SearchResultModel by lazy{
        SearchResultModel()
    }

    fun init(){

    }

    fun responseData(type: String, region: String, remainNoticeDate: Int, page: Int, limit: Int){
        searchResultModel.getSearchResulData(type, region, remainNoticeDate, page, limit, "")
    }

    fun requestData(data : ccc){
        view!!.responseData(data)
    }

    fun responseMoreSearchResultList(results : ArrayList<Content>){
        view!!.loadNextPage(results)
    }
}