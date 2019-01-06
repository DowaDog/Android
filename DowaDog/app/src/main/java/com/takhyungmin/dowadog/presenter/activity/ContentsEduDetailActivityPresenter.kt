package com.takhyungmin.dowadog.presenter.activity

import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.contents.activity.ContentsEduDetailActivity
import com.takhyungmin.dowadog.contents.adapter.ContentsEduDetailItem
import com.takhyungmin.dowadog.presenter.BasePresenter

class ContentsEduDetailActivityPresenter : BasePresenter<ContentsEduDetailActivity>() {

    lateinit var contentsEduDetailItems : ArrayList<ContentsEduDetailItem>

    fun initView(){
        contentsEduDetailItems = ArrayList()

        contentsEduDetailItems.add(ContentsEduDetailItem(R.drawable.pic1, "서브제모오오오오오옥1", "내요옹오오오오오오옹오오오오오오오옹1"))
        contentsEduDetailItems.add(ContentsEduDetailItem(R.drawable.pic1, "서브제모오오오오오옥2", "내요옹오오오오오오옹오오오오오오오옹2"))
        contentsEduDetailItems.add(ContentsEduDetailItem(R.drawable.pic1, "서브제모오오오오오옥3", "내요옹오오오오오오옹오오오오오오오옹3"))
        contentsEduDetailItems.add(ContentsEduDetailItem(R.drawable.pic1, "서브제모오오오오오옥4", "내요옹오오오오오오옹오오오오오오오옹4"))
        contentsEduDetailItems.add(ContentsEduDetailItem(R.drawable.pic1, "서브제모오오오오오옥5", "내요옹오오오오오오옹오오오오오오오옹5"))
        contentsEduDetailItems.add(ContentsEduDetailItem(R.drawable.pic1, "서브제모오오오오오옥6", "내요옹오오오오오오옹오오오오오오오옹6"))




        view!!.initView(contentsEduDetailItems)
    }
}