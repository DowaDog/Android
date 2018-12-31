package com.takhyungmin.dowadog.presenter.fragment

import com.takhyungmin.dowadog.home.fragment.HomeFragmentLargeSecondSlide
import com.takhyungmin.dowadog.presenter.BasePresenter

class HomeFragmentLargeSecondSlidePresenter : BasePresenter<HomeFragmentLargeSecondSlide>() {

    fun init(){
        view!!.init()
    }

    fun changeIndicator(position : Int){
        view!!.changeIndicator(position)
    }
}