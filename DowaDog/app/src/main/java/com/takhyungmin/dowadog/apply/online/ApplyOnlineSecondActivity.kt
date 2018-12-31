package com.takhyungmin.dowadog.apply.online

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jakewharton.rxbinding2.view.clicks
import com.takhyungmin.dowadog.R
import kotlinx.android.synthetic.main.activity_apply_online_second.*

class ApplyOnlineSecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_online_second)
        setOnBinding()
    }

    fun setOnBinding(){
        btn_online_apply_second_agree_check.clicks().subscribe {
            btn_online_apply_second_disagree_check.setImageResource(R.drawable.adopt_1step_check_grey)
            btn_online_apply_second_agree_check.setImageResource(R.drawable.adopt_1step_check_orange)
            layout_apply_second_input.visibility = View.VISIBLE
            btn_apply_online_second_next.setBackgroundColor(Color.parseColor("#e2e2e2"))
        }

        btn_online_apply_second_disagree_check.clicks().subscribe {
            btn_online_apply_second_agree_check.setImageResource(R.drawable.adopt_1step_check_grey)
            btn_online_apply_second_disagree_check.setImageResource(R.drawable.adopt_1step_check_orange)
            layout_apply_second_input.visibility = View.INVISIBLE
            btn_apply_online_second_next.setBackgroundColor(Color.parseColor("#ffc233"))
        }
    }
}
