package com.takhyungmin.dowadog.home.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.takhyungmin.dowadog.home.fragment.HomeFragmentLargeFristSlide
import com.takhyungmin.dowadog.home.fragment.HomeFragmentLargeSecondSlide

class HomeFragmentLargePadeAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val contentsCount = 2

    override fun getItem(position: Int) : Fragment =
            when(position){
                0 -> HomeFragmentLargeFristSlide()
                else -> HomeFragmentLargeSecondSlide()
            }


    override fun getCount(): Int = contentsCount
}