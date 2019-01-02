package com.takhyungmin.dowadog.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.takhyungmin.dowadog.R
import kotlinx.android.synthetic.main.custom_partly_complete_dog_single_response_dialog.*

class CustomPartlyCompleteDogDialog (context: Context,
                                     private val mCompleteContentNum: String,
                                     private val mResponseClickListener: View.OnClickListener?, private val responseText: String) : Dialog(context, android.R.style.Theme_Translucent_NoTitleBar) {

    private val clickedState = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lpWindow = WindowManager.LayoutParams()
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
        lpWindow.dimAmount = 0.55f
        window!!.attributes = lpWindow

        setContentView(R.layout.custom_partly_complete_dog_single_response_dialog)

        // 몇개인지 (뒤에 "개의" 필요)
        tv_complete_num_partly_complete_single_response_custom_dialog.text = mCompleteContentNum
        tv_right_partly_complete_single_response_custom_dialog.text = responseText

        if(mResponseClickListener != null){
            btn_response_partly_complete_single_response_custom_dialog.setOnClickListener(mResponseClickListener)
        }
    }

}