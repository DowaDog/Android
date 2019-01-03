package com.takhyungmin.dowadog.communitydetail

import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.View
import android.widget.LinearLayout
import com.jakewharton.rxbinding2.view.clicks
import com.takhyungmin.dowadog.BaseActivity
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.utils.CustomCommunityDetailDialog
import com.takhyungmin.dowadog.utils.CustomDialog
import kotlinx.android.synthetic.main.activity_community_detail.*
import kotlinx.android.synthetic.main.custom_dialog_community_detail.*

class CommunityDetailActivity : BaseActivity(), View.OnClickListener {

    lateinit var communityDetailRecyclerViewAdapter: CommunityDetailRecyclerViewAdapter

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


        // ## 댓글이 없으면 로직처리 확실하게 해야한다.
        val viewPagerItemData: ArrayList<String> = ArrayList()
        viewPagerItemData.add("https://s3.ap-northeast-2.amazonaws.com/liivlive/kb_login_profile_img.png")
        viewPagerItemData.add("https://s3.ap-northeast-2.amazonaws.com/liivlive/kb_login_profile_img.png")
        viewPagerItemData.add("https://s3.ap-northeast-2.amazonaws.com/liivlive/kb_login_profile_img.png")
        viewPagerItemData.add("https://s3.ap-northeast-2.amazonaws.com/liivlive/kb_login_profile_img.png")

        setViewPagerAdapter(viewPagerItemData)


        setEnterListener()
        init()

        setRVAdapter()
    }

    override fun onClick(v: View?) {
        when (v) {

            // 기능 더보기 버튼
            btn_three_dot_community_detail -> {

                // 내 게시물일 경우 글 수정, 글 삭제
                // modifyDeleteDialog.show()

                // 남의 게시물일 경우 신고하기
                reportDialog.show()
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
    }

    fun setViewPagerAdapter(viewPagerItemData: ArrayList<String>) {
        var communityDetailViewPagerAdapter = CommunityDetailViewPagerAdapter(this, viewPagerItemData)
        vp_community_detail_act.adapter = communityDetailViewPagerAdapter

        vp_community_detail_act.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels2: Int) {

            }

            override fun onPageSelected(position: Int) {
                // pageIndicatorView.selection = position
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

    private fun setRVAdapter() {

        var a: ArrayList<CommunityCommentData> = ArrayList()
        a.add(CommunityCommentData("https://s3.ap-northeast-2.amazonaws.com/liivlive/kb_login_profile_img.png", "김숙자", "이바보야진", "6시간전"))
        a.add(CommunityCommentData("https://s3.ap-northeast-2.amazonaws.com/liivlive/kb_login_profile_img.png", "김2숙자", "이바보야진짜아니야아직도나를ㄹ그렇게몰라 이바보야진짜아니야아직도나를ㄹ그렇게몰라 이바보야진짜아니야아직도나를ㄹ그렇게몰라", "6시간전"))
        a.add(CommunityCommentData("https://s3.ap-northeast-2.amazonaws.com/liivlive/kb_login_profile_img.png", "김2숙자", "이바보야진짜아니야아직도나를ㄹ그렇게몰라", "6시간전"))
        a.add(CommunityCommentData("https://s3.ap-northeast-2.amazonaws.com/liivlive/kb_login_profile_img.png", "김2숙자", "이바보야진짜아니야아직도나를ㄹ그렇게몰라", "6시간전"))
        a.add(CommunityCommentData("https://s3.ap-northeast-2.amazonaws.com/liivlive/kb_login_profile_img.png", "김2숙자", "이바보야진짜아니야아직도나를ㄹ그렇게몰라", "6시간전"))

        communityDetailRecyclerViewAdapter = CommunityDetailRecyclerViewAdapter(this@CommunityDetailActivity, a)
        rv_comment_community_detail_act.adapter = communityDetailRecyclerViewAdapter
        rv_comment_community_detail_act.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.VERTICAL, false)

    }

}


