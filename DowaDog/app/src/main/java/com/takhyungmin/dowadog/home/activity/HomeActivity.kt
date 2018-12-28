package com.takhyungmin.dowadog.home.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.community.CommunityFragment
import com.takhyungmin.dowadog.contents.fragment.ContentsFragment
import com.takhyungmin.dowadog.presenter.activity.HomeActivityPresenter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.navi_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var homeActivityPresenter : HomeActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //drawer_home.setStatusBarBackgroundColor(R.color.status2)
        initPresenter()
        setBinding()

    }

    override fun onStart() {
        super.onStart()
        homeActivityPresenter.initView()
    }

    fun initPresenter(){
        homeActivityPresenter = HomeActivityPresenter()
        homeActivityPresenter.view = this
    }



    fun addFragment(fragment : Fragment){
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.add(R.id.frame_home, fragment)
        transaction.commitNow()
    }

    fun replaceFragment(fragment : Fragment){
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.frame_home, fragment)
        transaction.commitNow()
    }

    fun setBinding(){
//        btn_home_drawer.clicks().subscribe {
//            if (!drawer_home.isDrawerOpen(Gravity.START))
//                drawer_home.openDrawer(Gravity.START)
//        }
        btn_home_drawer.setOnClickListener {
            if (!drawer_home.isDrawerOpen(Gravity.START))
                drawer_home.openDrawer(Gravity.START)
        }

        btn_navi_contents.setOnClickListener {
            homeActivityPresenter.replaceFragment(ContentsFragment())
            tv_home_title.text = "컨텐츠"
            if (drawer_home.isDrawerOpen(Gravity.START))
                drawer_home.closeDrawer(Gravity.START)
        }

        btn_navi_community.setOnClickListener {
            homeActivityPresenter.replaceFragment(CommunityFragment())
            tv_home_title.text = "커뮤니티"
            if (drawer_home.isDrawerOpen(Gravity.START))
                drawer_home.closeDrawer(Gravity.START)
        }

    }
}
