package com.takhyungmin.dowadog.presenter.activity

import com.takhyungmin.dowadog.home.model.get.GetDuplicateResponse
import com.takhyungmin.dowadog.login.model.LoginModel
import com.takhyungmin.dowadog.login.model.get.GetLoginData
import com.takhyungmin.dowadog.login.model.post.PostLoginDTO
import com.takhyungmin.dowadog.presenter.BasePresenter
import com.takhyungmin.dowadog.signup.activity.SignIdSettingActivity
import com.takhyungmin.dowadog.signup.model.SignUpModel
import okhttp3.MultipartBody

class SignIdSettingActivityPresenter : BasePresenter<SignIdSettingActivity>() {

    private val signIdSettingModel : SignUpModel by lazy {
        SignUpModel()
    }


    private val loginModel : LoginModel by lazy {
        LoginModel()
    }

    fun initView(){

    }

    //모델에게 일을 시킴
    fun requestData(id : String, password : String, name : String, birth : String,
                    phone : String, email : String, gender : String, deviceToken : String,
                    type : String, mimg: MultipartBody.Part?, pushAllow : String){
        signIdSettingModel.postSignIdSettingData(id, password, name, birth,
                phone, email, gender, deviceToken, type, mimg, pushAllow)
    }
    //view에게 데이터 전달
    fun responseData(id : String, pwd : String){
        view!!.responseData(id, pwd)
    }

    fun requestLoginFromSign(postLogin : PostLoginDTO){
        //0 : 회원가입, 1: 로그인.
        loginModel.postLoginFromSign(postLogin)
    }

    fun responseLoginFromSign(getLoginData: GetLoginData){
        view!!.successGetToken(getLoginData)
    }

    fun requestDuplicateData(id : String){
        signIdSettingModel.getDuplicateData(id)
    }

    fun responseDuplicateData(data : GetDuplicateResponse){
        view!!.responseDuplicateData(data)
    }

}