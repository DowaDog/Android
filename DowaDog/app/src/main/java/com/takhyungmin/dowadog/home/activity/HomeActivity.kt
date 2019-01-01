package com.takhyungmin.dowadog.home.activity

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.jakewharton.rxbinding2.view.clicks
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.community.CommunityFragment
import com.takhyungmin.dowadog.contents.fragment.ContentsFragment
import com.takhyungmin.dowadog.find.fragment.AnimalFindFragment
import com.takhyungmin.dowadog.home.HomeObject
import com.takhyungmin.dowadog.home.fragment.HomeFragment
import com.takhyungmin.dowadog.mypage.MypageActivity
import com.takhyungmin.dowadog.presenter.activity.HomeActivityPresenter
import com.takhyungmin.dowadog.search.SearchActivity
import com.takhyungmin.dowadog.utils.CustomTypeSpan
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.navi_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var homeActivityPresenter : HomeActivityPresenter
    private var clickedText : TextView? = null
    private lateinit var clickText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initPresenter()
        homeActivityPresenter.initView()
        setBinding()

    }


    fun initPresenter(){
        homeActivityPresenter = HomeActivityPresenter()
        homeActivityPresenter.view = this
        HomeObject.homeActivityPresenter = homeActivityPresenter
        textSize(tv_navi_home)
        clickedText = tv_navi_home
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

        btn_home_drawer.clicks().subscribe {
            if (!drawer_home.isDrawerOpen(Gravity.START))
                drawer_home.openDrawer(Gravity.START)
        }

        btn_navi_home.clicks().subscribe {
            btn_home_mypage.visibility = View.VISIBLE
            homeActivityPresenter.replaceFragment(HomeFragment())
            textSize(tv_navi_home)
            clickedText = tv_navi_home
            tv_home_title.text = "기다릴개"
            if (drawer_home.isDrawerOpen(Gravity.START))
                drawer_home.closeDrawer(Gravity.START)
        }

        btn_navi_contents.setOnClickListener {
            homeActivityPresenter.replaceFragment(ContentsFragment())
            btn_home_mypage.visibility = View.GONE
            btn_home_search.visibility = View.GONE

            tv_home_title.text = "컨텐츠"
            textSize(tv_navi_contents)
            clickedText = tv_navi_contents
            if (drawer_home.isDrawerOpen(Gravity.START))
                drawer_home.closeDrawer(Gravity.START)
        }

        btn_navi_community.setOnClickListener {
            homeActivityPresenter.replaceFragment(CommunityFragment())
            btn_home_mypage.visibility = View.GONE
            btn_home_search.visibility = View.GONE
            tv_home_title.text = "커뮤니티"
            textSize(tv_navi_community)
            clickedText = tv_navi_community
            if (drawer_home.isDrawerOpen(Gravity.START))
                drawer_home.closeDrawer(Gravity.START)
        }

//        btn_navi_adopt.setOnClickListener {
//            if (drawer_home.isDrawerOpen(Gravity.START))
//                drawer_home.closeDrawer(Gravity.START)
//            startActivity(Intent(this, ApplyOnlineMainActivity::class.java))
//        }



        btn_navi_adopt.clicks().subscribe {
            homeActivityPresenter.replaceFragment(AnimalFindFragment())
            btn_home_mypage.visibility = View.GONE
            tv_home_title.text = "입양 하기"
            btn_home_search.visibility = View.VISIBLE
            textSize(tv_navi_adopt)
            clickedText = tv_navi_adopt
            if (drawer_home.isDrawerOpen(Gravity.START))
                drawer_home.closeDrawer(Gravity.START)
        }

        btn_home_mypage.clicks().subscribe {
            if (drawer_home.isDrawerOpen(Gravity.START))
                drawer_home.closeDrawer(Gravity.START)
            startActivity(Intent(this, MypageActivity::class.java))
        }

        btn_navi_mypage.clicks().subscribe{
            if (drawer_home.isDrawerOpen(Gravity.START))
                drawer_home.closeDrawer(Gravity.START)
            startActivity(Intent(this, MypageActivity::class.java))
        }

        btn_home_search.clicks().subscribe {

            startActivity(Intent(this, SearchActivity::class.java))
        }

    }

    fun adjustDim(percent : Float){
        layout_home_dim.alpha = percent
    }

    fun textChange(){
        btn_home_mypage.visibility = View.GONE
        tv_home_title.text = "입양 하기"
        btn_home_search.visibility = View.VISIBLE
        textSize(tv_navi_adopt)
        clickedText = tv_navi_adopt
        if (drawer_home.isDrawerOpen(Gravity.START))
            drawer_home.closeDrawer(Gravity.START)
    }

    fun textSize(nowClickText : TextView){
        val font1 = Typeface.createFromAsset(assets, "nanum_square_extra_bold.ttf")
        val font2 = Typeface.createFromAsset(assets, "nanum_square_round_r.ttf")

        if(clickedText!=null){
            val ssb1 = SpannableStringBuilder(clickedText!!.text.toString())
            ssb1.setSpan(CustomTypeSpan("", font2), 0, clickedText!!.text.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
            clickedText!!.textSize = 16f
            clickedText!!.text = ssb1
        }
        val ssb2 = SpannableStringBuilder(nowClickText.text.toString())
        ssb2.setSpan(CustomTypeSpan("", font1), 0, nowClickText.text.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        nowClickText.textSize = 18f
        nowClickText.text = ssb2

    }
}
