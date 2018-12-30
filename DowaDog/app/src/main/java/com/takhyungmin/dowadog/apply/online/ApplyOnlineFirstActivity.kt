package com.takhyungmin.dowadog.apply.online

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jakewharton.rxbinding2.view.clicks
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.presenter.activity.ApplyOnlineFristActiviyPresenter
import kotlinx.android.synthetic.main.activity_apply_online_first.*

class ApplyOnlineFirstActivity : AppCompatActivity() {

    private lateinit var applyOnlineFirstActiviyPresenter: ApplyOnlineFristActiviyPresenter
    private var agreeClick = false
    private var disagreeClick = false
    private var next = false
    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_online_first)
        init()
        setBinding()
    }

    fun init(){
        applyOnlineFirstActiviyPresenter = ApplyOnlineFristActiviyPresenter()
        applyOnlineFirstActiviyPresenter.view = this
        applyOnlineFirstActiviyPresenter.initView()
    }

    private fun setBinding(){
        btn_online_apply_first_agree_check.clicks().subscribe {
            if(agreeClick){
                btn_online_apply_first_agree_check.setImageResource(R.drawable.adopt_1step_check_grey)
            }else{
                btn_online_apply_first_agree_check.setImageResource(R.drawable.adopt_1step_check_orange)
            }
            agreeClick = !agreeClick
        }

        btn_online_apply_first_disagree_check.clicks().subscribe {
            if(disagreeClick){
                btn_online_apply_first_disagree_check.setImageResource(R.drawable.adopt_1step_check_grey)
            }else{
                btn_online_apply_first_disagree_check.setImageResource(R.drawable.adopt_1step_check_orange)
            }
            disagreeClick = !disagreeClick
        }

        btn_apply_online_first_next.clicks().subscribe {
            if(next){
                startActivity(Intent(this, ApplyOnlineSecondActivity::class.java))
            }else{
                //btn_apply_online_first_next.setBackgroundColor(Color.parseColor("#e2e2e2"))
                //팝업
            }

            next = !next
        }
    }

    fun initView(){

    }
}
