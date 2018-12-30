package com.takhyungmin.dowadog

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.takhyungmin.dowadog.utils.CustomSingleResDialog
import kotlinx.android.synthetic.main.activity_apply_online_third_select_adopt.*

@Suppress("UNUSED_VALUE")
class ApplyOnlineThirdSelectAdoptActivity : BaseActivity(), View.OnClickListener {

    private var isCheckFlag = 0

    private val customDialog : CustomSingleResDialog by lazy {
        CustomSingleResDialog(ApplyOnlineThirdSelectAdoptActivity@ this, "필수 항목을 입력해주세요.",responseListener, "확인")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_online_third_select_adopt)

        init()
    }

    override fun onClick(v: View?) {
        when (v){
            btn_back_apply_online_third_select_adopt_act -> {
                finish()
            }

            btn_check_box_apply_online_third_select_adopt_act -> {
                if (isCheckFlag == 0) {
                    iv_check_box_apply_online_third_select_adopt_act.setImageResource(R.drawable.adopt_3step_check_orange)
                    isCheckFlag = 1
                    btn_next_apply_online_third_select_adopt_act.setBackgroundColor(Color.parseColor("#ffc233"))
                }else {
                    iv_check_box_apply_online_third_select_adopt_act.setImageResource(R.drawable.adopt_3step_check_gray)
                    isCheckFlag = 0
                    btn_next_apply_online_third_select_adopt_act.setBackgroundColor(Color.parseColor("#e2e2e2"))
                }
            }

            btn_next_apply_online_third_select_adopt_act -> {
                if (isCheckFlag == 0) {
                    // 다이얼로그 불러오기
                    openDemandValidResponseDialog()
                }else {
                    // 다음으로 넘어가기
                }
            }
        }
    }

    private fun init(){
        btn_back_apply_online_third_select_adopt_act.setOnClickListener(this)
        btn_check_box_apply_online_third_select_adopt_act.setOnClickListener(this)
        btn_next_apply_online_third_select_adopt_act.setOnClickListener(this)
    }

    private fun openDemandValidResponseDialog() {
        customDialog!!.show()
    }

    private val responseListener = View.OnClickListener { customDialog!!.dismiss() }

    fun aa(a: Int){

    }
}
