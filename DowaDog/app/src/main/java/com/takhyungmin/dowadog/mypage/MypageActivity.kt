package com.takhyungmin.dowadog.mypage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.takhyungmin.dowadog.BaseActivity
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.utils.CustomDialog
import kotlinx.android.synthetic.main.activity_mypage.*

class MypageActivity : BaseActivity() {

    val logoutCustomDialog : CustomDialog  by lazy {
        CustomDialog(this@MypageActivity, "로그아웃 하시겠습니까?", responseRight, responseLeft,"취소", "확인")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        btn_logout_setting_my_page_act.setOnClickListener {
            logoutCustomDialog!!.show()
        }
    }

    private val responseRight = View.OnClickListener { logoutCustomDialog!!.dismiss() }
    private val responseLeft = View.OnClickListener {
        logoutCustomDialog!!.dismiss()
        //##로그아웃
    }
}
