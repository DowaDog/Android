package com.takhyungmin.dowadog.presenter.activity

import com.takhyungmin.dowadog.communitydetail.CommunityDetailActivity
import com.takhyungmin.dowadog.communitydetail.model.CommunityDetailModel
import com.takhyungmin.dowadog.communitydetail.model.delete.DeleteCommunityCommentResponse
import com.takhyungmin.dowadog.communitydetail.model.delete.DeleteCommunityDetailPostResponse
import com.takhyungmin.dowadog.communitydetail.model.get.GetCommunityCommentResponse
import com.takhyungmin.dowadog.communitydetail.model.get.GetCommunityPostDetailResponse
import com.takhyungmin.dowadog.communitydetail.model.post.PostCommunityCommentWriteResponse
import com.takhyungmin.dowadog.presenter.BasePresenter

class CommunityDetailActivityPresenter : BasePresenter<CommunityDetailActivity>() {

    private val communityDetailModel : CommunityDetailModel by lazy {
        CommunityDetailModel()
    }

    fun initView(){

        //이 아래는 추후 모델을 쓸 일이 생기면 그때 바뀔 것.
        val viewPagerItemData: ArrayList<String> = ArrayList()
        viewPagerItemData.add("https://s3.ap-northeast-2.amazonaws.com/liivlive/kb_login_profile_img.png")
        viewPagerItemData.add("https://s3.ap-northeast-2.amazonaws.com/liivlive/kb_login_profile_img.png")
        viewPagerItemData.add("https://s3.ap-northeast-2.amazonaws.com/liivlive/kb_login_profile_img.png")
        viewPagerItemData.add("https://s3.ap-northeast-2.amazonaws.com/liivlive/kb_login_profile_img.png")
        view!!.setViewPagerAdapter(viewPagerItemData)

    }
    fun requestData(communityId: Int){
        communityDetailModel.getCommunityPostDetailData(communityId)
    }

    fun responseData(data : GetCommunityPostDetailResponse){
        view!!.responseData(data)
    }


    fun requestDeleteData(communityId: Int){
        communityDetailModel.deleteCommunityPostDetailData(communityId)
    }

    fun responseDeleteData(data: DeleteCommunityDetailPostResponse){
        view!!.deleteResponseData(data)
    }

    fun requestCommnetData(communityId: Int){
        communityDetailModel.getCommunityCommentData(communityId)
    }
    fun responseCommnetData(data: GetCommunityCommentResponse){
        view!!.responseCommentData(data)
    }

    fun requestCommentWriteData(communityId: Int, description: String){
        communityDetailModel.postCommunityCommentWriteRequest(communityId, description)
    }
    fun responseCommentWriteData(data: PostCommunityCommentWriteResponse){
        view!!.reponseCommentWriteData(data)
    }

    fun requestCommentDeleteData(commentId: Int){
        communityDetailModel.deleteCommunityCommentWriteRequest(commentId)
    }
    fun responseCommentDeleteData(data: DeleteCommunityCommentResponse){
        view!!.responseCommentDelelteData(data)
    }
}