package com.takhyungmin.dowadog.presenter.activity

import com.takhyungmin.dowadog.communitywrite.CommunityWriteActivity
import com.takhyungmin.dowadog.communitywrite.model.CommunityWriteModel
import com.takhyungmin.dowadog.communitywrite.model.post.PostCommunityPostWriteResponse
import com.takhyungmin.dowadog.presenter.BasePresenter
import okhttp3.MultipartBody

class CommunityWriteActivityPreseneter: BasePresenter<CommunityWriteActivity>() {

    private val communityWriteModel: CommunityWriteModel by lazy {
        CommunityWriteModel()
    }

    fun initView(){

    }

    fun requestData(input_title: String, input_contents: String, communityImgFiles: ArrayList<MultipartBody.Part>){
        communityWriteModel.getCommunityPostDetailData(input_title, input_contents, communityImgFiles)
    }

    fun responseData(data : PostCommunityPostWriteResponse){
        view!!.responseData(data)
    }
//
//    private val communityDetailModel : CommunityDetailModel by lazy {
//        CommunityDetailModel()
//    }
//
//
//    fun initView(){
//
//        //이 아래는 추후 모델을 쓸 일이 생기면 그때 바뀔 것.
//        val viewPagerItemData: ArrayList<String> = ArrayList()
//        viewPagerItemData.add("https://s3.ap-northeast-2.amazonaws.com/liivlive/kb_login_profile_img.png")
//        viewPagerItemData.add("https://s3.ap-northeast-2.amazonaws.com/liivlive/kb_login_profile_img.png")
//        viewPagerItemData.add("https://s3.ap-northeast-2.amazonaws.com/liivlive/kb_login_profile_img.png")
//        viewPagerItemData.add("https://s3.ap-northeast-2.amazonaws.com/liivlive/kb_login_profile_img.png")
//        view!!.setViewPagerAdapter(viewPagerItemData)
//    }
//    fun requestData(){
//        communityDetailModel.getCommunityPostDetailData()
//    }
//
//    fun responseData(data : GetCommunityPostDetailResponse){
//        view!!.responseData(data)
//    }
}