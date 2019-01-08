package com.takhyungmin.dowadog.communitydetail

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding2.view.clicks
import com.takhyungmin.dowadog.BaseActivity
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.communitydetail.model.delete.DeleteCommunityCommentResponse
import com.takhyungmin.dowadog.communitydetail.model.delete.DeleteCommunityDetailPostResponse
import com.takhyungmin.dowadog.communitydetail.model.get.CommunityCommentData
import com.takhyungmin.dowadog.communitydetail.model.get.GetCommunityCommentResponse
import com.takhyungmin.dowadog.communitydetail.model.get.GetCommunityPostDetailData
import com.takhyungmin.dowadog.communitydetail.model.get.GetCommunityPostDetailResponse
import com.takhyungmin.dowadog.communitydetail.model.post.PostCommunityCommentWriteResponse
import com.takhyungmin.dowadog.login.LoginActivity
import com.takhyungmin.dowadog.presenter.activity.CommunityDetailActivityPresenter
import com.takhyungmin.dowadog.utils.ApplicationData
import com.takhyungmin.dowadog.utils.CustomCommunityDetailDialog
import com.takhyungmin.dowadog.utils.CustomDialog
import kotlinx.android.synthetic.main.activity_community_detail.*
import kotlinx.android.synthetic.main.custom_dialog_community_detail.*
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*

class CommunityDetailActivity : BaseActivity(), View.OnClickListener {

    lateinit var communityDetailRecyclerViewAdapter: CommunityDetailRecyclerViewAdapter

    lateinit var communityCommentWriteData : PostCommunityCommentWriteResponse


    private lateinit var communityDetailActivityPresenter: CommunityDetailActivityPresenter

    private var communityId : Int = 9999

    // 0일 경우 글 수정
    // 1일 경우 글 삭제
    private var isModify:Int = 0


    //    lateinit var dialog : CustomCommunityDetailDialog

    val modifyDeleteDialog : CustomCommunityDetailDialog by lazy {
        CustomCommunityDetailDialog(this@CommunityDetailActivity, firstQuestionListener, secondQuestionListener,
                leftListener, rightListener, "취소", "확인")
    }

    val reportDialog : CustomDialog by lazy {
        CustomDialog(this@CommunityDetailActivity, "신고", reportDialogLeftListener, reportDialogRightListener
        , "취소", "확인")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_detail)


        communityId = intent.getIntExtra("communityId", 9999)

        initPresenter()
        communityDetailActivityPresenter.requestData(communityId)
        communityDetailActivityPresenter.requestCommnetData(communityId)
        setEnterListener()
        init()
    }

    override fun onClick(v: View?) {
        when (v) {

            // 기능 더보기 버튼
            btn_three_dot_community_detail -> {

                // 내 게시물일 경우 글 수정, 글 삭제
                modifyDeleteDialog.show()

                // 남의 게시물일 경우 신고하기
                // reportDialog.show()
            }

            // 댓글쓰기 버튼
            btn_comment_write_community_detail_act -> {

            }

            btn_back_community_detail -> {
                finish()
            }


        }
    }

    fun init() {
        btn_three_dot_community_detail.setOnClickListener(this)
        btn_back_community_detail.clicks().subscribe {
            finish()
        }
        btn_comment_write_community_detail_act.clicks().subscribe{
            Log.v("tagg",et_comment_write_community_detail_act.toString())
            if(ApplicationData.auth == "")
                logoutCustomDialog.show()
            else{
                communityDetailActivityPresenter.requestCommentWriteData(communityId, et_comment_write_community_detail_act.text.toString())
                et_comment_write_community_detail_act.text.clear()
            }
        }
    }

    val logoutCustomDialog : CustomDialog  by lazy {
        CustomDialog(this, "로그인이 필요한 서비스입니다.\n로그인 하시겠습니까?", responseRight, responseLeft,"취소", "확인")
    }

    private val responseRight = View.OnClickListener {

        logoutCustomDialog!!.dismiss()
    }
    private val responseLeft = View.OnClickListener {
        startActivity(Intent(this, LoginActivity::class.java))
        logoutCustomDialog!!.dismiss()
        //##로그아웃
    }




    fun setViewPagerAdapter(viewPagerItemData: ArrayList<String>) {
        var communityDetailViewPagerAdapter = CommunityDetailViewPagerAdapter(this, viewPagerItemData)
        vp_community_detail_act.adapter = communityDetailViewPagerAdapter
        pageIndicatorView.setCount(viewPagerItemData.size)

        vp_community_detail_act.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels2: Int) {

            }

            override fun onPageSelected(position: Int) {
                pageIndicatorView.selection = position
            }
        })
    }

    fun setEnterListener() {
        et_comment_write_community_detail_act.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if ((event!!.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    return true
                }
                return false
            }
        })
    }

    private val firstQuestionListener = View.OnClickListener {
        isModify = 1
        modifyDeleteDialog.tv_modify_custom_dialog_community_detail.setTextColor(Color.parseColor("#ffc233"))
        modifyDeleteDialog.tv_delete_custom_dialog_community_detail.setTextColor(Color.parseColor("#707070"))
    }
    private val secondQuestionListener = View.OnClickListener {
        isModify = 0
        modifyDeleteDialog.tv_modify_custom_dialog_community_detail.setTextColor(Color.parseColor("#707070"))
        modifyDeleteDialog.tv_delete_custom_dialog_community_detail.setTextColor(Color.parseColor("#ffc233")) }
    private val leftListener = View.OnClickListener {
        modifyDeleteDialog!!.dismiss()
        modifyDeleteDialog.tv_modify_custom_dialog_community_detail.setTextColor(Color.parseColor("#707070"))
        modifyDeleteDialog.tv_delete_custom_dialog_community_detail.setTextColor(Color.parseColor("#707070"))
    }
    private val rightListener = View.OnClickListener {
        // 글 수정 또는 글 삭제뷰로 이동
        if(isModify == 0){
            communityDetailActivityPresenter.requestDeleteData(communityId)
        }else {

        }
        modifyDeleteDialog.tv_modify_custom_dialog_community_detail.setTextColor(Color.parseColor("#707070"))
        modifyDeleteDialog.tv_delete_custom_dialog_community_detail.setTextColor(Color.parseColor("#707070"))
        modifyDeleteDialog!!.dismiss() }

    private val reportDialogLeftListener = View.OnClickListener {
        reportDialog!!.dismiss()
    }
    private val reportDialogRightListener = View.OnClickListener {

        // ## 신고하기 로직

        reportDialog!!.dismiss() }

//    fun setAppearKeyBoard(){
//        // 에딧텍스트에 포커스 맞춰 바로 키보드올라오게 하는 코드
//        et_comment_write_community_detail_act.requestFocus()
//        val imm: InputMethodManager = applicationContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.showSoftInput(et_comment_write_community_detail_act, InputMethodManager.SHOW_IMPLICIT)
//    }

    fun setRVAdapter(dataList: ArrayList<CommunityCommentData>) {
        communityDetailRecyclerViewAdapter = CommunityDetailRecyclerViewAdapter(this@CommunityDetailActivity, dataList)
        rv_comment_community_detail_act.adapter = communityDetailRecyclerViewAdapter
        rv_comment_community_detail_act.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.VERTICAL, false)
    }

    private fun initPresenter(){
        communityDetailActivityPresenter = CommunityDetailActivityPresenter()
        // 뷰 붙여주는 작엄
        communityDetailActivityPresenter.view = this
        CommunityDetailObject.communityDetailActivityPresenter= communityDetailActivityPresenter
    }

    fun responseData(data : GetCommunityPostDetailResponse){
        data?.let {
            if(it.data != null){
                viewBind(it.data)
            }
        }
    }


    fun deleteResponseData(data: DeleteCommunityDetailPostResponse){
        data?.let {
            if(data.message=="커뮤니티 정보 삭제 성공"){
                toast("커뮤니티 정보 삭제 성공")
                finish()
            }else {
                toast("커뮤니티 정보 삭제 실패")
            }
        }
    }

    fun responseCommentData(data : GetCommunityCommentResponse){
        data?.let {
            // 어뎁터 만들기
            if(data.data != null){
                setRVAdapter(data.data)
            }
        }
    }

    fun reponseCommentWriteData(data: PostCommunityCommentWriteResponse){
        data?.let {
            communityCommentWriteData = data
            // 리사이클러뷰 통신 다시하기 ## 비효율 ==> add로 해결 ? 고민 중
            communityDetailActivityPresenter.requestCommnetData(communityId)
        }
    }

    fun deleteComment(commentId : Int){
        communityDetailActivityPresenter.requestCommentDeleteData(commentId)
    }

    fun responseCommentDelelteData(data: DeleteCommunityCommentResponse){
        data?.let {
            // 리사이클러뷰 통신 다시하기 ## 비효율 ==> add로 해결 ? 고민 중
            if(data.message == "댓글 정보 삭제 성공"){
                toast("댓글 삭제 성공")
                communityDetailActivityPresenter.requestCommnetData(communityId)
            }else {
                toast("댓글 삭제 실패")
            }

        }
    }

    fun viewBind(data :GetCommunityPostDetailData){
        data.userProfileImg?.let{
            Glide.with(this@CommunityDetailActivity).load(data.userProfileImg).into(iv_writter2_community_detail_act)
        }
        data.userId?.let{
            tv_writter2_community_detail_act.text = data.userId
        }

        if(data.communityImgList != null || data.communityImgList.size > 0){
            val viewPagerItemData: ArrayList<String> = ArrayList()
            for (i in 0 until data.communityImgList.size){
                viewPagerItemData.add(data.communityImgList[i].filePath)
            }
            setViewPagerAdapter(viewPagerItemData)
        }else {
            rl_vp_community_detail_act.visibility = View.GONE
        }

        data.title?.let{
            tv_title_community_detail_act.text = it
        }

        data.createdAt?.let{

            if(data.today == true && data.today != null){
                val now = System.currentTimeMillis()
                val date = Date(now)
                val sdf = SimpleDateFormat("HH:mm:ss")
                // 현재시간 ex) 02:07:49
                val getPresentTime = sdf.format(date)
                // 현재시간 ex) 14:00:00
                var time = it.subSequence(11, 19)
                var pastTime = Integer.parseInt(getPresentTime.subSequence(0,2).toString()) - Integer.parseInt(time.subSequence(0,2).toString())
                tv_date_community_detail_act.text = pastTime.toString() + "시간 전"
                // 0시간에서 분으로 바꾸기.
                if(pastTime == 0){
                    pastTime = Integer.parseInt(getPresentTime.subSequence(3,5).toString()) - Integer.parseInt(time.subSequence(3,5).toString())
                    tv_date_community_detail_act.text = pastTime.toString() + "분 전"
                }

                if(pastTime == 0){
                    tv_date_community_detail_act.text = "방금 전"
                }
            }else if(data.today != null){
                tv_date_community_detail_act.text = data.createdAt.subSequence(0, 10).toString()
            }
        }

        data.detail?. let{
            tv_content_community_detail_act.text = it
        }

        data.presentUserImg?.let{
            Glide.with(this@CommunityDetailActivity).load(it).into(iv_writter_comment_community_detail_act2)
        }
    }
}


