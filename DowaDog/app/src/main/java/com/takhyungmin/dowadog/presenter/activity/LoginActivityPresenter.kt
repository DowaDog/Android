package com.takhyungmin.dowadog.presenter.activity

import com.takhyungmin.dowadog.login.LoginActivity
import com.takhyungmin.dowadog.login.model.LoginModel
import com.takhyungmin.dowadog.login.model.get.GetLoginData
import com.takhyungmin.dowadog.login.model.post.PostRefreshData
import com.takhyungmin.dowadog.presenter.BasePresenter

class LoginActivityPresenter : BasePresenter<LoginActivity>() {

    val loginModel : LoginModel by lazy {
        LoginModel()
    }

    fun requestLogin(id : String, pwd : String){
        loginModel.requestLogin(id, pwd)
    }

    fun responseLogin(data : GetLoginData){
        view!!.responseLogin(data)
    }

    fun requestRefresh(refresh : String){
        loginModel.postRefresh(refresh)
    }

    fun responseRefresh(data : PostRefreshData){
        view!!.responseRefresh(data)
    }

    fun toast(){
        view!!.toast()
    }
}