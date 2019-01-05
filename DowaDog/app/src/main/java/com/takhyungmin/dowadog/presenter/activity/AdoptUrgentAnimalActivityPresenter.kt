package com.takhyungmin.dowadog.presenter.activity

import com.takhyungmin.dowadog.adopt.activity.AdoptUrgentAnimalActivity
import com.takhyungmin.dowadog.adopt.model.AdoptAnimalModel
import com.takhyungmin.dowadog.adopt.model.get.UrgentAnimalData
import com.takhyungmin.dowadog.presenter.BasePresenter

class AdoptUrgentAnimalActivityPresenter : BasePresenter<AdoptUrgentAnimalActivity>() {

    val adoptAnimalModel : AdoptAnimalModel by lazy{
        AdoptAnimalModel()
    }
    fun requestUrgentList(page : Int, limit : Int){
        adoptAnimalModel.getAdoptUrgentPublicList(page, limit)
    }

    val firstResponseUrgentList = {urgentDatas : ArrayList<UrgentAnimalData> ->
        view!!.initView(urgentDatas)
    }

    val responseUrgentList = {urgentDatas : ArrayList<UrgentAnimalData> ->
        view!!.loadNextPage(urgentDatas)

    }

}