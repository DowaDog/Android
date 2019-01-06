package com.takhyungmin.dowadog.presenter.fragment

import com.takhyungmin.dowadog.contents.adapter.ContentsEduItem
import com.takhyungmin.dowadog.contents.fragment.ContentsEduFragment
import com.takhyungmin.dowadog.contents.model.ContentsModel
import com.takhyungmin.dowadog.contents.model.get.GetEduContentsContents
import com.takhyungmin.dowadog.presenter.BasePresenter

class ContentsEduFragmentPresenter : BasePresenter<ContentsEduFragment>() {

    val contentsModel : ContentsModel by lazy {
        ContentsModel()
    }
    lateinit var contentsEduItems : ArrayList<ContentsEduItem>
    fun initView(){


        view!!.initView(contentsEduItems)
    }

//    fun toDetail(width : Int, height : Int, left : Int, top : Int, title : String, sub : String){
//        view!!.toDetail(width, height, left, top, title, sub)
//    }

    val requestData = {
        contentsModel.requestList()
    }

    val responseList = {contents : ArrayList<GetEduContentsContents> ->
        view!!.responseList(contents)
    }

    fun toDetail(id : Int){
        view!!.toDetail(id)
    }

}