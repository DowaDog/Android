package com.takhyungmin.dowadog.presenter.activity

import com.takhyungmin.dowadog.presenter.BasePresenter
import com.takhyungmin.dowadog.scrap.ScrapActivity
import com.takhyungmin.dowadog.scrap.scrapmodel.ScrapModel
import com.takhyungmin.dowadog.scrap.scrapmodel.get.GetMyScrapResponse

class ScrapActivityPresenter : BasePresenter<ScrapActivity>() {

    val myScrapModel:ScrapModel by lazy {
        ScrapModel()
    }

    fun requestMyScrapList() {
        myScrapModel.getMyScrapList()
    }

    fun responseMyScrapList(data: GetMyScrapResponse) {
        view!!.responseScrapData(data)
    }
}