package com.takhyungmin.dowadog.contents.adapter

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.takhyungmin.dowadog.contents.ContentsEduFragment
import com.takhyungmin.dowadog.contents.ContentsSenseFragment

class ContentsAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val contentsCount = 2

    override fun getItem(p0: Int) =
            if (p0 == 0)
                ContentsEduFragment()
            else ContentsSenseFragment()

    override fun getCount(): Int = contentsCount
}