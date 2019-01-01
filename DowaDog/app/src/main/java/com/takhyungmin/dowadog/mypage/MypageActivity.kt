package com.takhyungmin.dowadog.mypage

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.jakewharton.rxbinding2.view.clicks
import com.takhyungmin.dowadog.BaseActivity
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.interest.InterestAnimalActivity
import com.takhyungmin.dowadog.scrap.ScrapActivity
import com.takhyungmin.dowadog.utils.CustomDialog
import kotlinx.android.synthetic.main.activity_mypage.*
import org.jetbrains.anko.startActivity

class MypageActivity : BaseActivity(), View.OnClickListener {

    override fun onClick(v: View?) {
        when(v)
        {
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
        }
    }

    val logoutCustomDialog : CustomDialog  by lazy {
        CustomDialog(this@MypageActivity, "로그아웃 하시겠습니까?", responseRight, responseLeft,"취소", "확인")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        init()
        setOnBinding()
    }

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
    }

    private val responseRight = View.OnClickListener { logoutCustomDialog!!.dismiss() }
    private val responseLeft = View.OnClickListener {
        logoutCustomDialog!!.dismiss()
        //##로그아웃
    }
}
