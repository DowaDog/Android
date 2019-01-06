package com.takhyungmin.dowadog.presenter.activity

import com.takhyungmin.dowadog.presenter.BasePresenter
import com.takhyungmin.dowadog.signup.SignInfoWriteActivity
import com.takhyungmin.dowadog.signup.model.get.GetSignInfoEmailResponse
import com.takhyungmin.dowadog.signup.model.get.SignInfoEmailModel

class SignInfoWriteActivityPresenter : BasePresenter<SignInfoWriteActivity>() {

    private val  signInfoEmailModel : SignInfoEmailModel by lazy {
        SignInfoEmailModel()
    }

    fun initView(){

    }

    //모델에게 일을 시킴
    fun requestData(){
        signInfoEmailModel.getSignInfoWriteData()
    }

    //view에게 데이터 전달
    fun responseData(data : GetSignInfoEmailResponse){
        view!!.responseData(data)
    }

}