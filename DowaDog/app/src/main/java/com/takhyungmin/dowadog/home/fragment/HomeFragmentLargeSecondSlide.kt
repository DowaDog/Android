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

    }


    fun init(){
        val slideAdapter = HomeFragmentPageAdpater(activity!!.supportFragmentManager)

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

    }

    fun changeIndicator(position : Int){
        when(position){
            0->{
                indicator_second_slide_first.setImageResource(R.drawable.adopt_1step_check_orange)
                indicator_second_slide_second.setImageResource(R.drawable.adopt_1step_check_grey)
                indicator_second_slide_third.setImageResource(R.drawable.adopt_1step_check_grey)
                indicator_second_slide_fourth.setImageResource(R.drawable.adopt_1step_check_grey)

                indicator_second_slide_first_text.setTextColor(Color.parseColor("#ffc233"))
                indicator_second_slide_second_text.setTextColor(Color.parseColor("#c7c7c7"))

            }
            1->{
                indicator_second_slide_first.setImageResource(R.drawable.adopt_1step_check_grey)
                indicator_second_slide_second.setImageResource(R.drawable.adopt_1step_check_orange)
                indicator_second_slide_third.setImageResource(R.drawable.adopt_1step_check_grey)
                indicator_second_slide_fourth.setImageResource(R.drawable.adopt_1step_check_grey)

                indicator_second_slide_first_text.setTextColor(Color.parseColor("#c7c7c7"))
                indicator_second_slide_second_text.setTextColor(Color.parseColor("#ffc233"))
                indicator_second_slide_third_text.setTextColor(Color.parseColor("#c7c7c7"))
            }
            2->{
                indicator_second_slide_first.setImageResource(R.drawable.adopt_1step_check_grey)
                indicator_second_slide_second.setImageResource(R.drawable.adopt_1step_check_grey)
                indicator_second_slide_third.setImageResource(R.drawable.adopt_1step_check_orange)
                indicator_second_slide_fourth.setImageResource(R.drawable.adopt_1step_check_grey)

                indicator_second_slide_second_text.setTextColor(Color.parseColor("#c7c7c7"))
                indicator_second_slide_third_text.setTextColor(Color.parseColor("#ffc233"))
                indicator_second_slide_fourth_text.setTextColor(Color.parseColor("#c7c7c7"))
            }
            3->{
                indicator_second_slide_first.setImageResource(R.drawable.adopt_1step_check_grey)
                indicator_second_slide_second.setImageResource(R.drawable.adopt_1step_check_grey)
                indicator_second_slide_third.setImageResource(R.drawable.adopt_1step_check_grey)
                indicator_second_slide_fourth.setImageResource(R.drawable.adopt_1step_check_orange)

                indicator_second_slide_third_text.setTextColor(Color.parseColor("#c7c7c7"))
                indicator_second_slide_fourth_text.setTextColor(Color.parseColor("#ffc233"))
            }
        }
    }
}