package com.takhyungmin.dowadog

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jakewharton.rxbinding2.view.clicks
import com.takhyungmin.dowadog.utils.HomeDialogFragmentStatePagerAdapter
import kotlinx.android.synthetic.main.activity_custom_home_dailog_activty.*

class CustomHomeDailogActivty : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_home_dailog_activty)

        setviewPagerListener()
        init()

    }

    fun setviewPagerListener(){
        vp_custom_home_dialog_activity.adapter = HomeDialogFragmentStatePagerAdapter(supportFragmentManager, 5)
        vp_custom_home_dialog_activity.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels2: Int) {

            }

            override fun onPageSelected(position: Int) {
                pageIndicatorView_dialog_activity.selection = position
                if(position == 4){
                    btn_start_custom_home_dialog_activity.visibility = View.VISIBLE
                }else {
                    btn_start_custom_home_dialog_activity.visibility = View.GONE
                }
            }
        })
    }

    fun init(){
        btn_start_custom_home_dialog_activity.clicks().subscribe(){
            finish()
        }
    }

}
