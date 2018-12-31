package com.takhyungmin.dowadog.utils

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

class CustomViewPager : ViewPager {

    var swipeEnabled = true

    constructor(context : Context) : super(context){

    }

    constructor(context : Context, attrs : AttributeSet) : super(context, attrs){

    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean =
            if (swipeEnabled) super.onInterceptTouchEvent(ev) else false



    override fun onTouchEvent(ev: MotionEvent?): Boolean =
            if (swipeEnabled) super.onTouchEvent(ev) else false


//    fun setPagingEnabled(enabled: Boolean) {
//        this.enabled = enabled
//    }


}
