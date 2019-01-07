package com.takhyungmin.dowadog.presenter.activity

import android.util.Log
import com.takhyungmin.dowadog.mypage.MypageSettingActivity
import com.takhyungmin.dowadog.mypage.model.get.GETMypageSettingResponse
import com.takhyungmin.dowadog.mypage.model.put.MypageSettingModel
import com.takhyungmin.dowadog.mypage.model.put.PUTMypageSettingResponse
import com.takhyungmin.dowadog.presenter.BasePresenter
import okhttp3.MultipartBody

class MypageSettingActivityPresenter : BasePresenter<MypageSettingActivity>() {

    private val mypageSettingModel : MypageSettingModel by lazy {
        MypageSettingModel()
    }

    fun initView() {

    }
    //개인정보 요청 get
    fun requestGetData(mimg : MultipartBody.Part?) {
        mypageSettingModel.getMypageSetData(mimg)
    }
    //개인정보 응답 get
    fun responseGetData(data : GETMypageSettingResponse) {
         view!!.responseGetData(data)
    }

    //모델에게 일을 시킴 put
    fun requestData(mimage : MultipartBody.Part?){
        Log.v("TAGG", "mypagesetting 프레젠터 리퀘스트데이터")
        mypageSettingModel.putMypageSetData(mimage)
    }
    //view에게 데이터 전달 put
    fun responseData(data : PUTMypageSettingResponse){
        Log.v("TAGG", "프레젠터 리스폰스데이터")
        view!!.responseData(data)
    }
}