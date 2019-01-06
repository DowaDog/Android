package com.takhyungmin.dowadog.introduce

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jakewharton.rxbinding2.view.clicks
import com.takhyungmin.dowadog.R
import kotlinx.android.synthetic.main.activity_introduce.*

class IntroduceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setContentView(R.layout.activity_introduce)
        setOnBinding()
    }

    fun setOnBinding(){
        btn_introduce_close.clicks().subscribe {
            finish()
        }
    }
}
