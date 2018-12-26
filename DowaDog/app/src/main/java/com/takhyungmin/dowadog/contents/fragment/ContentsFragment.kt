package com.takhyungmin.dowadog.contents.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.contents.adapter.ContentsAdapter
import com.takhyungmin.dowadog.presenter.fragment.ContentsFragmentPresenter
import kotlinx.android.synthetic.main.fragment_contents.*

class ContentsFragment : Fragment(){

    lateinit var contentsFragmentPresenter: ContentsFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contents, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentsFragmentPresenter = ContentsFragmentPresenter()
        contentsFragmentPresenter.view = this
    }

    override fun onStart() {
        super.onStart()
        tab_contents.addTab(tab_contents.newTab().setText("교육"))
        tab_contents.addTab(tab_contents.newTab().setText("상식"))

        val tabAdapter = ContentsAdapter(childFragmentManager)

        vp_contents.adapter = tabAdapter
        vp_contents.currentItem = 0
        vp_contents.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_contents))


        tab_contents.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabSelected(tab: TabLayout.Tab?) {
                vp_contents.currentItem = tab!!.position
            }
        })
    }
}