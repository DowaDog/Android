package com.takhyungmin.dowadog.home.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.clicks
import com.mancj.slideup.SlideUp
import com.mancj.slideup.SlideUpBuilder
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.home.HomeObject
import com.takhyungmin.dowadog.home.adapter.HomeFragmentLargePadeAdapter
import com.takhyungmin.dowadog.presenter.fragment.HomeFragmentPresenter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home_slide_up.*


class HomeFragment : Fragment() {
    lateinit var slideUp : SlideUp
    lateinit var homeFragmentPresneter : HomeFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeFragmentPresneter = HomeFragmentPresenter()
        homeFragmentPresneter.view = this
        HomeObject.homeFragmentPresenter = homeFragmentPresneter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeFragmentPresneter.init()
        setBinding()
    }


    override fun onStart() {
        super.onStart()

    }

    fun init(){
        val slideAdapter = HomeFragmentLargePadeAdapter(childFragmentManager)

        vp_home_fragment_slide_contents.adapter = slideAdapter
        vp_home_fragment_slide_contents.currentItem = 0

        vp_home_fragment_slide_contents.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
//                if(p0 == 1)
//                    vp_home_fragment_slide_contents.swipeEnabled = false
                //enable 되는 시점
                //p0 != 1
                //작은 페이지의 포지션이 0일 때
                homeFragmentPresneter.changeIndicator(p0)
            }

        })


        slideUp = SlideUpBuilder(layout_home_fragment_slide_pannel)
                .withListeners(object : SlideUp.Listener.Events {
                    override fun onSlide(percent: Float) {
                        layout_home_fragment_dim.alpha = 1 - percent / 100
                        content_slide_up_view.alpha = 1 - percent/100
                        if (btn_home_fragment_slide.isShown && percent < 100) {
                            btn_home_fragment_slide.visibility = View.GONE
                        }
                    }

                    override fun onVisibilityChanged(visibility: Int) {
                        if (visibility == View.GONE) {
                            btn_home_fragment_slide.visibility = View.VISIBLE
                        }
                    }
                })
                .withGesturesEnabled(true)
                .withStartState(SlideUp.State.HIDDEN)
                .withSlideFromOtherView(btn_home_fragment_slide)
                .build()
    }

    fun setBinding(){
        btn_home_fragment_slide.clicks().subscribe {
            slideUp.show()
        }
    }

    fun swipeEnable(position : Int){
        when(position){
            0 -> {
                vp_home_fragment_slide_contents.swipeEnabled = true
            }
            else -> {
                vp_home_fragment_slide_contents.swipeEnabled = false
            }
        }

    }

    fun changeIndicator(position : Int){
        when(position){
            0->{
                indicator_slide_first.setImageResource(R.drawable.dot_indicator_blue)
                indicator_slide_second.setImageResource(R.drawable.dot_indicator_white)
                indicator_slide_third.setImageResource(R.drawable.dot_indicator_white)
                indicator_slide_fourth.setImageResource(R.drawable.dot_indicator_white)
                indicator_slide_fifth.setImageResource(R.drawable.dot_indicator_white)
            }
            1->{
                indicator_slide_first.setImageResource(R.drawable.dot_indicator_white)
                indicator_slide_second.setImageResource(R.drawable.dot_indicator_blue)
                indicator_slide_third.setImageResource(R.drawable.dot_indicator_white)
                indicator_slide_fourth.setImageResource(R.drawable.dot_indicator_white)
                indicator_slide_fifth.setImageResource(R.drawable.dot_indicator_white)
            }
            2->{
                indicator_slide_first.setImageResource(R.drawable.dot_indicator_white)
                indicator_slide_second.setImageResource(R.drawable.dot_indicator_white)
                indicator_slide_third.setImageResource(R.drawable.dot_indicator_blue)
                indicator_slide_fourth.setImageResource(R.drawable.dot_indicator_white)
                indicator_slide_fifth.setImageResource(R.drawable.dot_indicator_white)
            }
            3->{
                indicator_slide_first.setImageResource(R.drawable.dot_indicator_white)
                indicator_slide_second.setImageResource(R.drawable.dot_indicator_white)
                indicator_slide_third.setImageResource(R.drawable.dot_indicator_white)
                indicator_slide_fourth.setImageResource(R.drawable.dot_indicator_blue)
                indicator_slide_fifth.setImageResource(R.drawable.dot_indicator_white)
            }
            4->{
                indicator_slide_first.setImageResource(R.drawable.dot_indicator_white)
                indicator_slide_second.setImageResource(R.drawable.dot_indicator_white)
                indicator_slide_third.setImageResource(R.drawable.dot_indicator_white)
                indicator_slide_fourth.setImageResource(R.drawable.dot_indicator_white)
                indicator_slide_fifth.setImageResource(R.drawable.dot_indicator_blue)
            }
        }
    }
}