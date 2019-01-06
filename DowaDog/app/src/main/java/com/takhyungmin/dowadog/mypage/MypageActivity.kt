package com.takhyungmin.dowadog.mypage

import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.view.View
import com.takhyungmin.dowadog.R.id.*
import com.takhyungmin.dowadog.scrap.ScrapActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding2.view.clicks
import com.takhyungmin.dowadog.BaseActivity
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.interest.InterestAnimalActivity
import com.takhyungmin.dowadog.letter.LetterActivity
import com.takhyungmin.dowadog.mypage.model.Data
import com.takhyungmin.dowadog.mypage.model.MypageObject
import com.takhyungmin.dowadog.mypage.model.get.GETMypageResponse
import com.takhyungmin.dowadog.presenter.activity.MypageActivityPresenter
import com.takhyungmin.dowadog.utils.CustomDialog
import kotlinx.android.synthetic.main.activity_mypage.*
import kotlinx.android.synthetic.main.activity_mypage_setting.*
import org.jetbrains.anko.startActivity

class MypageActivity : BaseActivity(), View.OnClickListener {

    override fun onClick(v: View?) {
        when(v)
        {
            //로그아웃 버튼
            btn_logout_setting_my_page_act-> {
                logoutCustomDialog!!.show()

            }
            //마이페이지 설정
            btn_next_setting_mypage_act -> {
                startActivity<MypageSettingActivity>()
            }
            btn_back_mypage_act -> {
                finish()
            }
            rl_mailbox_mypage_act -> {
                startActivity<LetterActivity>()
            }

        }
    }

    val logoutCustomDialog : CustomDialog  by lazy {
        CustomDialog(this@MypageActivity, "로그아웃 하시겠습니까?", responseRight, responseLeft,"취소", "확인")
    }

    private lateinit var mypageActivityPresenter: MypageActivityPresenter

    lateinit var mypageGetData : Data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        init()
        setOnBinding()

        initPresenter()
        mypageActivityPresenter.initView()
        mypageActivityPresenter.requestData()

    }

    //JAX-RS문법..?
    fun setOnBinding(){
        btn_mypage_interest.clicks().subscribe {
            startActivity(Intent(this, InterestAnimalActivity::class.java))
        }

        btn_mypage_scrap.clicks().subscribe {
            startActivity(Intent(this, ScrapActivity::class.java))
        }

        btn_mypage_mine.clicks().subscribe {
            startActivity(Intent(this, ScrapActivity::class.java))
        }
    }

    private fun init() {
        btn_logout_setting_my_page_act.setOnClickListener(this)
        btn_next_setting_mypage_act.setOnClickListener(this)
        btn_back_mypage_act.setOnClickListener(this)
        rl_mailbox_mypage_act.setOnClickListener(this)
    }

    private val responseRight = View.OnClickListener {
        logoutCustomDialog!!.dismiss()
    }
    private val responseLeft = View.OnClickListener {
        logoutCustomDialog!!.dismiss()
        //##로그아웃
    }

    fun responseData(data: GETMypageResponse) {

        data?.let {

            mypageGetData = data.data
            //여기에 받아온 데이터들을 가져와서 보여주는 것을 해야함 (함수로 만들던 여기에 구현하던)
            Log.v("TAGG", mypageGetData.toString())


            Glide.with(this@MypageActivity)
                    .load(mypageGetData.profileImg)
                    .thumbnail(0.1f)
                    .into(img_profile_mypage_act)

            tv_username_mypage_act.text = mypageGetData.userName + "님"
            tv_interest_animal_mypage_act.text = mypageGetData.userLike.toString()
            tv_scrap_num_mypage_act.text = mypageGetData.userScrap.toString()
            tv_write_num_mypage_act.text = mypageGetData.userCommunity.toString()
            if(mypageGetData.mailboxUpdated)
            {
                img_new_mypage_act.visibility = View.VISIBLE
            }else{
                img_new_mypage_act.visibility = View.GONE
            }


        }
    }

    //view에 presenter 붙여주기
    private fun initPresenter() {

         mypageActivityPresenter= MypageActivityPresenter()
        // 뷰 붙여주는 작업
        mypageActivityPresenter.view = this
        MypageObject.mypageActivityPresenter = mypageActivityPresenter

        Log.v("TAGG", "엑티비티 이닛프레젠터")

    }

}
