package com.takhyungmin.dowadog.home.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.clicks
import com.mancj.slideup.SlideUp
import com.mancj.slideup.SlideUpBuilder
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.home.adapter.HomeFragmentPageAdpater
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home_slide_up.*


class HomeFragment : Fragment() {
    lateinit var slideUp : SlideUp

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onStart() {
        super.onStart()

        val slideAdapter = HomeFragmentPageAdpater(activity!!.supportFragmentManager)

        vp_home_fragment_slide_contents.adapter = slideAdapter
        vp_home_fragment_slide_contents.currentItem = 0


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
                .withLoggingEnabled(true)
                .withGesturesEnabled(true)
                .withStartState(SlideUp.State.HIDDEN)
                .withSlideFromOtherView(btn_home_fragment_slide)
                .build()

        setBinding()

    }

    fun setBinding(){
        btn_home_fragment_slide.clicks().subscribe {
            slideUp.show()
        }
    }
}