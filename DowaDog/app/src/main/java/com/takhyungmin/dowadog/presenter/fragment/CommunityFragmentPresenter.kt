package com.takhyungmin.dowadog.presenter.fragment

import com.takhyungmin.dowadog.community.CommunityFragment
import com.takhyungmin.dowadog.community.model.CommunityModel
import com.takhyungmin.dowadog.community.model.get.GetCommunityContents
import com.takhyungmin.dowadog.presenter.BasePresenter

class CommunityFragmentPresenter : BasePresenter<CommunityFragment>() {

    lateinit var communityItems : ArrayList<GetCommunityContents>

    private val communityModel : CommunityModel by lazy {
        CommunityModel()
    }



    fun initView(page : Int, limit : Int){
        communityModel.getDuplicateData(page, limit)
    }

    fun toDetail(communityId: Int){
        view!!.toDetail(communityId)
    }




    fun nextPage(page : Int, limit : Int){
        //인자는 통신할 때 쓰일 예정
        view!!.loadNextPage(communityItems)
    }


    fun responseCommunityFirstList(contents : ArrayList<GetCommunityContents>){
        view!!.initView(contents)
    }

    fun requestCommunityList(page : Int, limit : Int){
        communityModel.getDuplicateData(page, limit)
    }

    fun responseCommunityList(contents : ArrayList<GetCommunityContents>){
        view!!.loadNextPage(contents)
    }

}