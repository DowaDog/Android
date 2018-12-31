package com.takhyungmin.dowadog.home.fragment

import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.utils.CustomTypeSpan
import kotlinx.android.synthetic.main.fragment_home_large_first_slide.*

class HomeFragmentLargeFristSlide : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_large_first_slide, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onStart() {
        super.onStart()


    }

    fun init(){
        val font1 = Typeface.createFromAsset(activity!!.assets, "nanum_square_extra_bold.ttf")
        val font2 = Typeface.createFromAsset(activity!!.assets, "nanum_square_light.ttf")

        val ssb = SpannableStringBuilder("단계별로\n필요한 정보를 확인하세요.")
        ssb.setSpan(CustomTypeSpan("", font1), 0, 3, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        ssb.setSpan(CustomTypeSpan("", font2), 4, "단계별로\n필요한 정보를 확인하세요.".length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)

        tv_home_slide_first_text.text = ssb
    }
}