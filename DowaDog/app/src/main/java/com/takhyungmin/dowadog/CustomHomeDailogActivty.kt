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

        rl_root_box_home_idalog_activity.setClipToOutline(true)
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
                if(position == 4){
                    btn_start_custom_home_dialog_activity.visibility = View.VISIBLE
                    rl_indicator_community_detail_act.visibility = View.GONE
                }else {
                    btn_start_custom_home_dialog_activity.visibility = View.GONE
                    rl_indicator_community_detail_act.visibility = View.VISIBLE
                }
                setIndicaotrChange(position)

            }
        })
    }

    fun init(){
        btn_start_custom_home_dialog_activity.clicks().subscribe(){
            finish()
        }
    }

    fun setIndicaotrChange(position : Int){
        when(position){
            0 ->{
                iv_first_indicator_home_dialog.setImageResource(R.drawable.dot_indicator_orange)
                iv_second_indicator_home_dialog.setImageResource(R.drawable.dot_indicator_gray)
                iv_third_indicator_home_dialog.setImageResource(R.drawable.dot_indicator_gray)
                iv_fourth_indicator_home_dialog.setImageResource(R.drawable.dot_indicator_gray)
                iv_fifth_indicator_home_dialog.setImageResource(R.drawable.dot_indicator_gray)
            }
            1 ->{
                iv_first_indicator_home_dialog.setImageResource(R.drawable.dot_indicator_gray)
                iv_second_indicator_home_dialog.setImageResource(R.drawable.dot_indicator_orange)
                iv_third_indicator_home_dialog.setImageResource(R.drawable.dot_indicator_gray)
                iv_fourth_indicator_home_dialog.setImageResource(R.drawable.dot_indicator_gray)
                iv_fifth_indicator_home_dialog.setImageResource(R.drawable.dot_indicator_gray)
            }
            2 ->{
                iv_first_indicator_home_dialog.setImageResource(R.drawable.dot_indicator_gray)
                iv_second_indicator_home_dialog.setImageResource(R.drawable.dot_indicator_gray)
                iv_third_indicator_home_dialog.setImageResource(R.drawable.dot_indicator_orange)
                iv_fourth_indicator_home_dialog.setImageResource(R.drawable.dot_indicator_gray)
                iv_fifth_indicator_home_dialog.setImageResource(R.drawable.dot_indicator_gray)
            }
            3 ->{
                iv_first_indicator_home_dialog.setImageResource(R.drawable.dot_indicator_gray)
                iv_second_indicator_home_dialog.setImageResource(R.drawable.dot_indicator_gray)
                iv_third_indicator_home_dialog.setImageResource(R.drawable.dot_indicator_gray)
                iv_fourth_indicator_home_dialog.setImageResource(R.drawable.dot_indicator_orange)
                iv_fifth_indicator_home_dialog.setImageResource(R.drawable.dot_indicator_gray)
            }
//            4 ->{
//                iv_first_indicator_home_dialog.setImageResource(R.drawable.dot_indicator_gray)
//                iv_second_indicator_home_dialog.setImageResource(R.drawable.dot_indicator_gray)
//                iv_third_indicator_home_dialog.setImageResource(R.drawable.dot_indicator_gray)
//                iv_fourth_indicator_home_dialog.setImageResource(R.drawable.dot_indicator_gray)
//                iv_fifth_indicator_home_dialog.setImageResource(R.drawable.dot_indicator_orange)
//            }
        }
    }

}
