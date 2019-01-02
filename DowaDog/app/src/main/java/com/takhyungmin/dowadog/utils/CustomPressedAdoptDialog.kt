package com.takhyungmin.dowadog.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.takhyungmin.dowadog.R
import kotlinx.android.synthetic.main.custom_pressed_adopt_act_dialog.*

class CustomPressedAdoptDialog(context: Context,
                               private val mShelterName: String,
                               private val mShelterNum: String,
                               private val mLeftClickListener: View.OnClickListener?,
                               private val mRightClickListener: View.OnClickListener?, private val leftText: String, private val rightText: String) : Dialog(context, android.R.style.Theme_Translucent_NoTitleBar) {

    private val clickedState = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lpWindow = WindowManager.LayoutParams()
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
        // 투명도
        lpWindow.dimAmount = 0.55f
        // window에 환경적용
        window!!.attributes = lpWindow

        setContentView(R.layout.custom_pressed_adopt_act_dialog)

        tv_description_pressed_adopt_act_custom_dialog.text = mShelterName
        tv_shelter_num_pressed_adopt_act_custom_dialog.text = mShelterNum
        tv_left_custom_pressed_adopt_act_dialog.text = leftText
        tv_right_custom_pressed_adopt_act_dialog.text = rightText

        if (mLeftClickListener != null && mRightClickListener != null) {
            btn_left_custom_pressed_adopt_act_dialog.setOnClickListener(mLeftClickListener)
            btn_right_custom_pressed_adopt_act_dialog.setOnClickListener(mRightClickListener)
        } else if (mLeftClickListener != null && mRightClickListener == null) {
            btn_left_custom_pressed_adopt_act_dialog.setOnClickListener(mLeftClickListener)
        } else {

        }
    }

}