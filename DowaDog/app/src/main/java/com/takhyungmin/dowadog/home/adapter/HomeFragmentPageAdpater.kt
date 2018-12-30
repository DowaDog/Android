package com.takhyungmin.dowadog.home.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.takhyungmin.dowadog.home.fragment.*

class HomeFragmentPageAdpater(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val contentsCount = 5

    override fun getItem(position: Int) : Fragment =
        when(position){
            0 -> HomeFragmentFirstSlide()
            1 -> HomeFragmentSecondSlide()
            2 -> HomeFragmentThirdSlide()
            3 -> HomeFragmentFourthSlide()
            else -> HomeFragmentFifthSlide()
        }


    override fun getCount(): Int = contentsCount
}