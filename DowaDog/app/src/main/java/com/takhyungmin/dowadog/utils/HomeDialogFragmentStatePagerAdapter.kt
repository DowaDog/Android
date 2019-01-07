package com.takhyungmin.dowadog.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.Log

class HomeDialogFragmentStatePagerAdapter(fm: FragmentManager, val fragmentCount: Int) : FragmentStatePagerAdapter(fm) {
    init {
        Log.v("adapter", "adapter2")
    }
    override fun getItem(position: Int): Fragment? {
        Log.v("adapter", "adapter3")

        when (position) {
            0 -> return HomeDialogFirstFragment()
            1 -> return HomeDialogSecondFragment()
            2 -> return HomeDialogThirdFragment()
            3 -> return HomeDialogFourthFragment()
            4 -> return HomeDialogFifthFragment()
            else -> return null
        }
    }

    override fun getCount(): Int = fragmentCount
}