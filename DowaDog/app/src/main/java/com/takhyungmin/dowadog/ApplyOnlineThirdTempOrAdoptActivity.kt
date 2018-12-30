package com.takhyungmin.dowadog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import com.takhyungmin.dowadog.utils.CustomSingleResDialog
import kotlinx.android.synthetic.main.activity_apply_online_third_temp_or_adopt.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.textColor



class ApplyOnlineThirdTempOrAdoptActivity : BaseActivity(), View.OnClickListener {

    private var isTempProtection : Int = 0

    private var isAdopt : Int = 0

    private val customDialog : CustomSingleResDialog by lazy {
        CustomSingleResDialog(ApplyOnlineThirdTempOrAdoptActivity@ this, "임보와 입양 중 선택해주세요.",responseListener, "확인")
    }




    override fun onClick(v: View?) {
        when (v) {
            btn_back_apply_online_third_temp_or_adopt_act -> {

                finish()
            }

            btn_temp_protect_apply_online_third_temp_ro_adopt_act -> {

                if (isTempProtection == 0){
                    iv_temp_protect_apply_online_third_temp_ro_adopt_act.setImageResource(R.color.mainCarrotColor)
                    tv_temp_protect_apply_online_third_temp_ro_adopt_act.textColor = Color.parseColor("#FFFFFF")
                    isTempProtection = 1

                    iv_adopt_apply_online_third_temp_or_adopt_act.setImageResource(R.color.mainGrayColor)
                    tv_adopt_apply_online_third_temp_or_adopt_act.textColor = Color.parseColor("#707070")
                    isAdopt = 0

                    btn_next_apply_online_third_temp_or_adopt_act.setBackgroundColor(Color.parseColor("#ffc233"))
                }else {
                    iv_temp_protect_apply_online_third_temp_ro_adopt_act.setImageResource(R.color.mainGrayColor)
                    tv_temp_protect_apply_online_third_temp_ro_adopt_act.textColor = Color.parseColor("#707070")
                    isTempProtection = 0

                    btn_next_apply_online_third_temp_or_adopt_act.setBackgroundColor(Color.parseColor("#f0f0f0"))
                }


            }

            btn_adopt_apply_online_third_temp_or_adopt_act -> {

                if (isAdopt == 0){
                    iv_adopt_apply_online_third_temp_or_adopt_act.setImageResource(R.color.mainCarrotColor)
                    tv_adopt_apply_online_third_temp_or_adopt_act.textColor = Color.parseColor("#FFFFFF")
                    isAdopt = 1

                    iv_temp_protect_apply_online_third_temp_ro_adopt_act.setImageResource(R.color.mainGrayColor)
                    tv_temp_protect_apply_online_third_temp_ro_adopt_act.textColor = Color.parseColor("#707070")
                    isTempProtection = 0

                    btn_next_apply_online_third_temp_or_adopt_act.setBackgroundColor(Color.parseColor("#ffc233"))

                }else {
                    iv_adopt_apply_online_third_temp_or_adopt_act.setImageResource(R.color.mainGrayColor)
                    tv_adopt_apply_online_third_temp_or_adopt_act.textColor = Color.parseColor("#707070")
                    isAdopt = 0

                    btn_next_apply_online_third_temp_or_adopt_act.setBackgroundColor(Color.parseColor("#f0f0f0"))
                }
            }

            btn_next_apply_online_third_temp_or_adopt_act -> {

                if (isAdopt ==0 && isTempProtection ==0){
                    openDemandValidResponseDialog()
                }else {
                    // 다음으로 넘어가기
                    if(isAdopt == 1){
                        startActivity<ApplyOnlineThirdSelectAdoptActivity>()
                    }else {
                        startActivity<ApplyOnlineThirdSelectTempActivity>()
                    }
                }

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_online_third_temp_or_adopt)

        init()




    }

    private fun init(){
        btn_back_apply_online_third_temp_or_adopt_act.setOnClickListener(this)
        btn_temp_protect_apply_online_third_temp_ro_adopt_act.setOnClickListener(this)
        btn_adopt_apply_online_third_temp_or_adopt_act.setOnClickListener(this)
        btn_next_apply_online_third_temp_or_adopt_act.setOnClickListener(this)
    }

    fun openDemandValidResponseDialog() {
        customDialog.window.setBackgroundDrawable(ColorDrawable(Color.parseColor("#47000000")))
        customDialog!!.show()
    }

    private val responseListener = View.OnClickListener { customDialog!!.dismiss() }

}
