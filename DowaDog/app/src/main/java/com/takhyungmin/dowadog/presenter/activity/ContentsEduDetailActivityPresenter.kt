package com.takhyungmin.dowadog.presenter.activity

import android.util.Log
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.contents.activity.ContentsEduDetailActivity
import com.takhyungmin.dowadog.contents.adapter.ContentsEduDetailItem
import com.takhyungmin.dowadog.contents.model.get.ContentsEduDetailModel
import com.takhyungmin.dowadog.contents.model.get.GETContentsEduDetailResponse
import com.takhyungmin.dowadog.presenter.BasePresenter

class ContentsEduDetailActivityPresenter : BasePresenter<ContentsEduDetailActivity>() {

    lateinit var contentsEduDetailItems : ArrayList<ContentsEduDetailItem>

    fun initView(){
        contentsEduDetailItems = ArrayList()

        contentsEduDetailItems.add(ContentsEduDetailItem(R.drawable.pic1, "서브제모오오오오오옥1", "내요옹오오오오오오옹오오오오오오오옹1"))
        contentsEduDetailItems.add(ContentsEduDetailItem(R.drawable.pic1, "서브제모오오오오오옥2", "내요옹오오오오오오옹오오오오오오오옹2"))
        contentsEduDetailItems.add(ContentsEduDetailItem(R.drawable.pic1, "서브제모오오오오오옥3", "내요옹오오오오오오옹오오오오오오오옹3"))
        contentsEduDetailItems.add(ContentsEduDetailItem(R.drawable.pic1, "서브제모오오오오오옥4", "내요옹오오오오오오옹오오오오오오오옹4"))
        contentsEduDetailItems.add(ContentsEduDetailItem(R.drawable.pic1, "서브제모오오오오오옥5", "내요옹오오오오오오옹오오오오오오오옹5"))
        contentsEduDetailItems.add(ContentsEduDetailItem(R.drawable.pic1, "서브제모오오오오오옥6", "내요옹오오오오오오옹오오오오오오오옹6"))




        view!!.initView(contentsEduDetailItems)
    }

    private val contentsEduDetailModel : ContentsEduDetailModel by lazy {
        ContentsEduDetailModel()
    }


    //모델에게 일을 시킴
    fun requestData(id : Int){
        Log.v("TAGG", "Edu Detail 프레젠터 리퀘스트데이터")
        contentsEduDetailModel.getContentsEduDetailData(id)
    }
    //view에게 데이터 전달
    fun responseData(data : GETContentsEduDetailResponse){
        Log.v("TAGG", "Edu Detail 프레젠터 리스폰스데이터")
        view!!.responseData(data)
    }

    fun requestScrap(id : Int){
        contentsEduDetailModel.postEduContentsScrap(id)
    }

    fun responseScrap(clear : Boolean){
        view!!.responseScrap(clear)
    }

    fun requestComplete(id : Int){
        contentsEduDetailModel.postEduContentsComplete(id)
    }

    fun responseComplete(clear : Boolean){
        view!!.responseComplete(clear)
    }
}
