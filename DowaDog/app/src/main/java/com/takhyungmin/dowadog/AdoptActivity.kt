package com.takhyungmin.dowadog

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.takhyungmin.dowadog.utils.CustomSingleResDialog
import kotlinx.android.synthetic.main.activity_adopt.*

class AdoptActivity : BaseActivity(), View.OnClickListener {

    private var isRealVisit = 0
    private var isOnlineApply = 0
    lateinit var customDialog: CustomSingleResDialog
    override fun onClick(v: View?) {
        when(v){
            btn_back_adopt_act -> {
                finish()
            }
            btn_real_visit_adopt_act -> {
                if(isRealVisit == 0){
                    btn_real_visit_adopt_act.setImageResource(R.drawable.visit_orange_1227)
                    isRealVisit = 1
                    btn_online_apply_adopt_act.setImageResource(R.drawable.online_apply_gray_1227)
                    isOnlineApply = 0
                    btn_next_adopt_act.setBackgroundColor(Color.parseColor("#ffc233"))
                }else {
                    btn_real_visit_adopt_act.setImageResource(R.drawable.visit_gray_1227)
                    isRealVisit = 0
                    btn_next_adopt_act.setBackgroundColor(Color.parseColor("#e2e2e2"))
                }
            }
            btn_online_apply_adopt_act -> {
                if(isOnlineApply == 0){
                    btn_online_apply_adopt_act.setImageResource(R.drawable.online_apply_orange_1227)
                    isOnlineApply = 1
                    btn_real_visit_adopt_act.setImageResource(R.drawable.visit_gray_1227)
                    isRealVisit = 0
                    btn_next_adopt_act.setBackgroundColor(Color.parseColor("#ffc233"))
                }else {
                    btn_online_apply_adopt_act.setImageResource(R.drawable.online_apply_gray_1227)
                    isOnlineApply = 0
                    btn_next_adopt_act.setBackgroundColor(Color.parseColor("#e2e2e2"))
                }
            }
            btn_next_adopt_act -> {

                // 아무것도 선택 안되었을 때
                if(isOnlineApply == 0 && isRealVisit ==0){
                    openDialog()
                }else {
                    // 통신
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adopt)
        init()
    }

    private fun init(){
        btn_real_visit_adopt_act.setOnClickListener(this)
        btn_online_apply_adopt_act.setOnClickListener(this)
        btn_next_adopt_act.setOnClickListener(this)
        btn_back_adopt_act.setOnClickListener(this)
    }

    private fun openDialog() {
        val content = "입양 방법을 선택해주세요!"
        customDialog = CustomSingleResDialog(AdoptActivity@ this, content, responseListener, "확인")
        customDialog!!.show()

    }

    private val responseListener = View.OnClickListener { customDialog!!.dismiss() }
}
