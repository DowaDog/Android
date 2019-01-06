package com.takhyungmin.dowadog.presenter.activity

import com.takhyungmin.dowadog.presenter.BasePresenter
import com.takhyungmin.dowadog.scrap.MyCommunityPostActivity
import com.takhyungmin.dowadog.scrap.model.MyCommunityPostModel
import com.takhyungmin.dowadog.scrap.model.get.GetMyCommunityPostResponse

class MyCommunityPostActivityPresenter : BasePresenter<MyCommunityPostActivity>() {
    val myCommunityPostModel : MyCommunityPostModel by lazy{
        MyCommunityPostModel()
    }

    fun requestMyCommunityPostList(){
        myCommunityPostModel.getMyCommunityPost()
    }

    fun requestMyCommunityPostList(data : GetMyCommunityPostResponse){
        view!!.requestMyCommunityPostList(data)
    }
}