package com.takhyungmin.dowadog.presenter.activity

import android.util.Log
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.contents.activity.ContentsSenseDetailActivity
import com.takhyungmin.dowadog.contents.adapter.ContentsSenseDetailItem
import com.takhyungmin.dowadog.contents.model.get.ContentSenseDetailResponse
import com.takhyungmin.dowadog.contents.model.get.ContentsEduDetailModel
import com.takhyungmin.dowadog.presenter.BasePresenter


class ContentsSenseDetailActivityPresenter : BasePresenter<ContentsSenseDetailActivity>() {

    lateinit var contentsSenseDetailItems : ArrayList<ContentsSenseDetailItem>

    fun initView(){
        contentsSenseDetailItems = ArrayList()

        contentsSenseDetailItems.add(ContentsSenseDetailItem(R.drawable.pic1, "서브제모오오오오오옥1", "내요옹오오오오오오옹오오오오오오오옹1"))
        contentsSenseDetailItems.add(ContentsSenseDetailItem(R.drawable.pic1, "서브제모오오오오오옥2", "내요옹오오오오오오옹오오오오오오오옹2"))
        contentsSenseDetailItems.add(ContentsSenseDetailItem(R.drawable.pic1, "서브제모오오오오오옥3", "내요옹오오오오오오옹오오오오오오오옹3"))
        contentsSenseDetailItems.add(ContentsSenseDetailItem(R.drawable.pic1, "서브제모오오오오오옥4", "내요옹오오오오오오옹오오오오오오오옹4"))
        contentsSenseDetailItems.add(ContentsSenseDetailItem(R.drawable.pic1, "서브제모오오오오오옥5", "내요옹오오오오오오옹오오오오오오오옹5"))
        contentsSenseDetailItems.add(ContentsSenseDetailItem(R.drawable.pic1, "서브제모오오오오오옥6", "내요옹오오오오오오옹오오오오오오오옹6"))


        //view!!.initView(contentsSenseDetailItems)
    }

    private val contentsSenseDetailModel : ContentsEduDetailModel by lazy {
        ContentsEduDetailModel()
    }


    //모델에게 일을 시킴
    fun requestData(id : Int){
        Log.v("TAGG", "sense Detail 프레젠터 리퀘스트데이터")
        contentsSenseDetailModel.getContentsSenseDetailData(id)
    }
    //view에게 데이터 전달
    fun responseData(data : ContentSenseDetailResponse){
        Log.v("TAGG", "sense Detail 프레젠터 리스폰스데이터")
        view!!.responseSenseData(data)
    }
}