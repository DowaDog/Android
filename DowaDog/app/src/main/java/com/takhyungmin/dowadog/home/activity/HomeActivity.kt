package com.takhyungmin.dowadog.home.activity

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.util.Log
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
import com.takhyungmin.dowadog.home.model.get.GetDuplicateResponse
import com.takhyungmin.dowadog.mypage.MypageActivity
import com.takhyungmin.dowadog.presenter.activity.HomeActivityPresenter
import com.takhyungmin.dowadog.utils.CustomTypeSpan
import kotlinx.android.synthetic.main.activity_apply_online_main.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.navi_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var homeActivityPresenter : HomeActivityPresenter
    var clickedText : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initPresenter()
        homeActivityPresenter.initView()
        homeActivityPresenter.requestData()
        setBinding()
    }

    fun responseData(data : GetDuplicateResponse){
        Log.v("data", data.message)
    }


    fun initPresenter(){
        homeActivityPresenter = HomeActivityPresenter()
        homeActivityPresenter.view = this
        HomeObject.homeActivityPresenter = homeActivityPresenter
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
        btn_home_drawer.clicks().subscribe {
            if (!drawer_home.isDrawerOpen(Gravity.START))
                drawer_home.openDrawer(Gravity.START)
        }


        btn_navi_home.clicks().subscribe {
            homeActivityPresenter.replaceFragment(HomeFragment())
            layout_home_base.elevation = 1f
            btn_home_search.visibility = View.GONE
            btn_home_mypage.visibility = View.VISIBLE
            tv_home_title.text = "기다릴개"
            if (drawer_home.isDrawerOpen(Gravity.START))
                drawer_home.closeDrawer(Gravity.START)
        }

        btn_navi_contents.clicks().subscribe {
            homeActivityPresenter.replaceFragment(ContentsFragment())
            layout_home_base.elevation = 0f
            btn_home_mypage.visibility = View.GONE
            btn_home_search.visibility = View.GONE
            tv_home_title.text = "컨텐츠"
            if (drawer_home.isDrawerOpen(Gravity.START))
                drawer_home.closeDrawer(Gravity.START)
        }

        btn_navi_community.clicks().subscribe {
            homeActivityPresenter.replaceFragment(CommunityFragment())
            layout_home_base.elevation = 0f
            btn_home_mypage.visibility = View.GONE
            btn_home_search.visibility = View.GONE
            tv_home_title.text = "커뮤니티"
            if (drawer_home.isDrawerOpen(Gravity.START))
                drawer_home.closeDrawer(Gravity.START)
        }

        btn_navi_adopt.clicks().subscribe {
            layout_home_base.elevation = 1f
            btn_home_mypage.visibility = View.GONE
            btn_home_search.visibility = View.GONE
            homeActivityPresenter.replaceFragment(AnimalFindFragment())
            tv_home_title.text = "입양 하기"
            btn_home_search.visibility = View.VISIBLE

            if (drawer_home.isDrawerOpen(Gravity.START))
                drawer_home.closeDrawer(Gravity.START)
        }

        btn_navi_mypage.clicks().subscribe {
            startActivity(Intent(this, MypageActivity::class.java))
            if (drawer_home.isDrawerOpen(Gravity.START))
                drawer_home.closeDrawer(Gravity.START)
        }

        btn_home_mypage.clicks().subscribe {

            startActivity(Intent(this, MypageActivity::class.java))
        }

//        btn_navi_adopt_info.clicks().subscribe {
//            homeActivityPresenter.replaceFragment(AnimalFindFragment())
//            btn_home_mypage.visibility = View.GONE
//            tv_home_title.text = "동물 찾기"
//            btn_home_search.visibility = View.VISIBLE
//            if (drawer_home.isDrawerOpen(Gravity.START))
//                drawer_home.closeDrawer(Gravity.START)
//        }

    }

    fun textSizeChange(textView : TextView){
        val font1 = Typeface.createFromAsset(assets, "nanum_square_extra_bold.ttf")
        val font2 = Typeface.createFromAsset(assets, "nanum_square_light.ttf")


        val ssb1 = SpannableStringBuilder(textView.text.toString())
        val ssb2 = SpannableStringBuilder(clickedText!!.text.toString())

        ssb1.setSpan(CustomTypeSpan("", font1), 0, textView.text.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        ssb2.setSpan(CustomTypeSpan("", font2), 0, clickedText!!.text.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)

        tv_online_apply_main_infotext.text = ssb1
        clickedText!!.text = ssb2
    }

    fun adjustDim(percent : Float){
        layout_home_dim.alpha = percent
    }

    fun toNew(){
        btn_home_mypage.visibility = View.GONE
        btn_home_search.visibility = View.VISIBLE
    }
}
