package com.takhyungmin.dowadog.presenter.activity

import android.support.v4.app.Fragment
import com.takhyungmin.dowadog.home.activity.HomeActivity
import com.takhyungmin.dowadog.home.fragment.HomeFragment
import com.takhyungmin.dowadog.presenter.BasePresenter

class HomeActivityPresenter : BasePresenter<HomeActivity>() {

    fun initView(){
        view!!.addFragment(HomeFragment())
    }

    fun replaceFragment(fragment : Fragment){
        view!!.replaceFragment(fragment)
    }
}