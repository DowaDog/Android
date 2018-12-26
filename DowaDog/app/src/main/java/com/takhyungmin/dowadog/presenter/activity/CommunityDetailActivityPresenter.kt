package com.takhyungmin.dowadog.presenter.activity

import com.takhyungmin.dowadog.communitydetail.CommunityDetailActivity
import com.takhyungmin.dowadog.presenter.BasePresenter

class CommunityDetailActivityPresenter : BasePresenter<CommunityDetailActivity>() {


    fun initView(){

        //이 아래는 추후 모델을 쓸 일이 생기면 그때 바뀔 것.
        val viewPagerItemData: ArrayList<String> = ArrayList()
        viewPagerItemData.add("https://s3.ap-northeast-2.amazonaws.com/liivlive/kb_login_profile_img.png")
        viewPagerItemData.add("https://s3.ap-northeast-2.amazonaws.com/liivlive/kb_login_profile_img.png")
        viewPagerItemData.add("https://s3.ap-northeast-2.amazonaws.com/liivlive/kb_login_profile_img.png")
        viewPagerItemData.add("https://s3.ap-northeast-2.amazonaws.com/liivlive/kb_login_profile_img.png")



        view!!.setViewPagerAdapter(viewPagerItemData)
    }
}