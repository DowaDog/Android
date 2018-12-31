package com.takhyungmin.dowadog.presenter.fragment

import com.takhyungmin.dowadog.home.fragment.HomeFragment
import com.takhyungmin.dowadog.presenter.BasePresenter

class HomeFragmentPresenter : BasePresenter<HomeFragment>() {
    fun init(){
        view!!.init()
    }

    fun changeIndicator(position : Int){
        view!!.changeIndicator(position)
    }

    fun swipeEnable(position : Int){
        view!!.swipeEnable(position)
    }
}