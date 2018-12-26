package com.takhyungmin.dowadog

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setGrayStatusBarIcon()
    }

    fun setGrayStatusBarIcon(){
        // 밑에 두줄 아이콘 회색 + 흰색바탕
        val view: View? = window.decorView
        view!!.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
    }
}