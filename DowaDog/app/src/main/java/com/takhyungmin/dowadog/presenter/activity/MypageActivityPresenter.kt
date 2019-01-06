package com.takhyungmin.dowadog.presenter.activity

import android.util.Log
import com.takhyungmin.dowadog.mypage.MypageActivity
import com.takhyungmin.dowadog.mypage.model.get.GETMypageResponse
import com.takhyungmin.dowadog.mypage.model.get.MypageModel
import com.takhyungmin.dowadog.presenter.BasePresenter

class MypageActivityPresenter : BasePresenter<MypageActivity>() {

    private val mypageModel : MypageModel by lazy {
        MypageModel()
    }

    fun initView() {

    }

    //모델에게 일을 시킴
    fun requestData(){
        Log.v("TAGG", "프레젠터 리퀘스트데이터")
        mypageModel.getMypageData()
    }
    //view에게 데이터 전달
    fun responseData(data : GETMypageResponse){
        Log.v("TAGG", "프레젠터 리스폰스데이터")
        view!!.responseData(data)
    }

}