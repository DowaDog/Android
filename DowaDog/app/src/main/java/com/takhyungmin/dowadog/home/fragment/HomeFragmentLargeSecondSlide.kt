package com.takhyungmin.dowadog.home.fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.home.HomeObject
import com.takhyungmin.dowadog.home.adapter.HomeFragmentPageAdpater
import com.takhyungmin.dowadog.presenter.fragment.HomeFragmentLargeSecondSlidePresenter
import kotlinx.android.synthetic.main.fragment_home_large_second_slide.*

class HomeFragmentLargeSecondSlide : Fragment(){

    lateinit var homeFragmentLargeSecondSlidePresenter: HomeFragmentLargeSecondSlidePresenter
    var currentPage = 0

    val DELAY_MS: Long = 500//delay in milliseconds before task is to be executed
    val PERIOD_MS: Long = 3000
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_large_second_slide, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeFragmentLargeSecondSlidePresenter = HomeFragmentLargeSecondSlidePresenter()
        homeFragmentLargeSecondSlidePresenter.view = this
    }


    override fun onStart() {
        super.onStart()
        homeFragmentLargeSecondSlidePresenter.init()
        tv_fragment_home_large_second_description.setText(R.string.home_slide_description1)

    }


    fun init(){


        val slideAdapter = HomeFragmentPageAdpater(childFragmentManager)

        vp_home_fragment_second_slide_contents.adapter = slideAdapter
        vp_home_fragment_second_slide_contents.currentItem = 0

        vp_home_fragment_second_slide_contents.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {

                HomeObject.homeFragmentPresenter.changeIndicator(p0+1)
                HomeObject.homeFragmentPresenter.swipeEnable(p0)

                homeFragmentLargeSecondSlidePresenter.changeIndicator(p0)

            }
        })

//        val handler = Handler()
//        val Update = Runnable {
//            if (currentPage == 4) {
//                currentPage = 0
//            }
//            vp_home_fragment_second_slide_contents.setCurrentItem(currentPage++, true)
//        }
//
//        val timer = Timer() // This will create a new Thread
//        timer.schedule(object : TimerTask() { // task to be scheduled
//
//            override fun run() {
//                handler.post(Update)
//            }
//        }, DELAY_MS, PERIOD_MS)
    }



    fun changeIndicator(position : Int){
        when(position){
            0->{
                indicator_second_slide_first.setImageResource(R.drawable.slider_write_orange)
                indicator_second_slide_second.setImageResource(R.drawable.slider_call_gray)
                indicator_second_slide_third.setImageResource(R.drawable.slider_adopt_gray)
                indicator_second_slide_fourth.setImageResource(R.drawable.slider_finish_gray)

                indicator_second_slide_first_text.setTextColor(Color.parseColor("#ffc233"))
                indicator_second_slide_second_text.setTextColor(Color.parseColor("#c7c7c7"))

                tv_fragment_home_large_second_description.setText(R.string.home_slide_description1)

            }
            1->{
                indicator_second_slide_first.setImageResource(R.drawable.slider_write_gray)
                indicator_second_slide_second.setImageResource(R.drawable.slider_call_orange)
                indicator_second_slide_third.setImageResource(R.drawable.slider_adopt_gray)
                indicator_second_slide_fourth.setImageResource(R.drawable.slider_finish_gray)

                indicator_second_slide_first_text.setTextColor(Color.parseColor("#c7c7c7"))
                indicator_second_slide_second_text.setTextColor(Color.parseColor("#ffc233"))
                indicator_second_slide_third_text.setTextColor(Color.parseColor("#c7c7c7"))

                tv_fragment_home_large_second_description.setText(R.string.home_slide_description2)

            }
            2->{
                indicator_second_slide_first.setImageResource(R.drawable.slider_write_gray)
                indicator_second_slide_second.setImageResource(R.drawable.slider_call_gray)
                indicator_second_slide_third.setImageResource(R.drawable.slider_adopt_orange)
                indicator_second_slide_fourth.setImageResource(R.drawable.slider_finish_gray)

                indicator_second_slide_second_text.setTextColor(Color.parseColor("#c7c7c7"))
                indicator_second_slide_third_text.setTextColor(Color.parseColor("#ffc233"))
                indicator_second_slide_fourth_text.setTextColor(Color.parseColor("#c7c7c7"))

                tv_fragment_home_large_second_description.setText(R.string.home_slide_description3)

            }
            3->{
                indicator_second_slide_first.setImageResource(R.drawable.slider_write_gray)
                indicator_second_slide_second.setImageResource(R.drawable.slider_call_gray)
                indicator_second_slide_third.setImageResource(R.drawable.slider_adopt_gray)
                indicator_second_slide_fourth.setImageResource(R.drawable.slider_finish_orange)

                indicator_second_slide_third_text.setTextColor(Color.parseColor("#c7c7c7"))
                indicator_second_slide_fourth_text.setTextColor(Color.parseColor("#ffc233"))
                tv_fragment_home_large_second_description.setText(R.string.home_slide_description4)

            }
        }
    }
}