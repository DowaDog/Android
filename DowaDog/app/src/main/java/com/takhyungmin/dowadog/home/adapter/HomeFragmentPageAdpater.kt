package com.takhyungmin.dowadog.home.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.takhyungmin.dowadog.home.fragment.HomeFragmentFirstSlide
import com.takhyungmin.dowadog.home.fragment.HomeFragmentFourthSlide
import com.takhyungmin.dowadog.home.fragment.HomeFragmentSecondSlide
import com.takhyungmin.dowadog.home.fragment.HomeFragmentThirdSlide

class HomeFragmentPageAdpater(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val contentsCount = 4

    override fun getItem(position: Int) : Fragment =
        when(position){
            0 -> HomeFragmentFirstSlide()
            1 -> HomeFragmentSecondSlide()
            2 -> HomeFragmentThirdSlide()
            else -> HomeFragmentFourthSlide()
        }


    override fun getCount(): Int = contentsCount
}