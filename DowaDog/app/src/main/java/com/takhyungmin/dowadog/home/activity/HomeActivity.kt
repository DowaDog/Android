package com.takhyungmin.dowadog.home.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.contents.fragment.ContentsFragment
import com.takhyungmin.dowadog.presenter.activity.HomeActivityPresenter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.navi_home.*

class HomeActivity : AppCompatActivity() {

    lateinit var homeActivityPresenter : HomeActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
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
        transaction.commit()
    }

    fun replaceFragment(fragment : Fragment){
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.frame_home, fragment)
        transaction.commit()
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

    }
}
