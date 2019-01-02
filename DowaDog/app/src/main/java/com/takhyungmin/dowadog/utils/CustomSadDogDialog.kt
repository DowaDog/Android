package com.takhyungmin.dowadog.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.takhyungmin.dowadog.R
import kotlinx.android.synthetic.main.custom_sad_dog_single_response_dialog.*

class CustomSadDogDialog (context: Context,
                          private val mContent: String,
                          private val mResponseClickListener: View.OnClickListener?, private val responseText: String) : Dialog(context, android.R.style.Theme_Translucent_NoTitleBar) {

    private val clickedState = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lpWindow = WindowManager.LayoutParams()
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
        lpWindow.dimAmount = 0.55f
        window!!.attributes = lpWindow

        setContentView(R.layout.custom_sad_dog_single_response_dialog)

        tv_description_sad_dog_single_response_custom_dialog.text = mContent
        tv_right_sad_dog_single_response_custom_dialog.text = responseText

        if(mResponseClickListener != null){
            btn_response_sad_dog_single_response_custom_dialog.setOnClickListener(mResponseClickListener)
        }
    }

}