package com.takhyungmin.dowadog.presenter.activity

import com.takhyungmin.dowadog.apply.online.ApplyOnlineFirstActivity
import com.takhyungmin.dowadog.presenter.BasePresenter

class ApplyOnlineFristActiviyPresenter : BasePresenter<ApplyOnlineFirstActivity>() {

    fun initView(){
        //Model에서 저장된 것들 가져오기.

        view!!.initView()
    }


}