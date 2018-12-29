package com.takhyungmin.dowadog.apply.online

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.takhyungmin.dowadog.R

class ApplyOnlineFirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_online_first)
    }
}
