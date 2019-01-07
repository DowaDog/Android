package com.takhyungmin.dowadog.presenter.fragment

import android.util.Log
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


//    fun toDetail(width : Int, height : Int, left : Int, top : Int, title : String, sub : String){
//        view!!.toDetail(width, height, left, top, title, sub)
//    }

    val requestData = {
        Log.v("들어옴1", "들어옴1")
        contentsModel.requestList()
    }

    val responseList = {contents : ArrayList<GetEduContentsContents> ->
        view!!.responseList(contents)
    }

    fun toDetail(id : Int, num : Int, image : String){
        view!!.toDetail(id, num, image)
    }

}