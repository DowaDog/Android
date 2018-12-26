package com.takhyungmin.dowadog.communitydetail

import android.content.Context
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.takhyungmin.dowadog.R
import kotlinx.android.synthetic.main.activity_community_detail.*

class CommunityDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_detail)

        val viewPagerItemData: ArrayList<String> = ArrayList()
        viewPagerItemData.add("https://s3.ap-northeast-2.amazonaws.com/liivlive/kb_login_profile_img.png")
        viewPagerItemData.add("https://s3.ap-northeast-2.amazonaws.com/liivlive/kb_login_profile_img.png")
        viewPagerItemData.add("https://s3.ap-northeast-2.amazonaws.com/liivlive/kb_login_profile_img.png")
        viewPagerItemData.add("https://s3.ap-northeast-2.amazonaws.com/liivlive/kb_login_profile_img.png")

        setViewPagerAdapter(viewPagerItemData)

        setEnterListener()

        setAppearKeyBoard()
    }

    fun setViewPagerAdapter(viewPagerItemData : ArrayList<String>){
        var communityDetailViewPagerAdapter = CommunityDetailViewPagerAdapter(this, viewPagerItemData)
        vp_community_detail_act.adapter = communityDetailViewPagerAdapter

        vp_community_detail_act.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float,positionOffsetPixels2: Int) {

            }

            override fun onPageSelected(position: Int) {
                // pageIndicatorView.selection = position
            }
        })
    }
    
    fun setEnterListener(){
        et_comment_write_community_detail_act.setOnKeyListener(object: View.OnKeyListener{
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if((event!!.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    return true
                }
                return false
            }
        })
    }

    fun setAppearKeyBoard(){
        // 에딧텍스트에 포커스 맞춰 바로 키보드올라오게 하는 코드
        et_comment_write_community_detail_act.requestFocus()
        val imm: InputMethodManager = applicationContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(et_comment_write_community_detail_act, InputMethodManager.SHOW_IMPLICIT)
    }

}


