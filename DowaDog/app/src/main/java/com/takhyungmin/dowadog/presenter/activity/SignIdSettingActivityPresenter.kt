package com.takhyungmin.dowadog.presenter.activity

import com.takhyungmin.dowadog.presenter.BasePresenter
import com.takhyungmin.dowadog.signup.SignIdSettingActivity
import com.takhyungmin.dowadog.signup.model.post.PostSignIdSettingResponse
import com.takhyungmin.dowadog.signup.model.post.SignIdSettingModel
import okhttp3.MultipartBody

class SignIdSettingActivityPresenter : BasePresenter<SignIdSettingActivity>() {

    private val signIdSettingModel : SignIdSettingModel by lazy {
        SignIdSettingModel()
    }

    fun initView(){

    }

    //모델에게 일을 시킴
    fun requestData(mimg : MultipartBody.Part?){
        signIdSettingModel.postSignIdSettingData(mimg)
    }
    //view에게 데이터 전달
    fun responseData(data : PostSignIdSettingResponse){
        view!!.responseData(data)
    }


}