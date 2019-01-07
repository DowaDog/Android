package com.takhyungmin.dowadog.donation

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.jakewharton.rxbinding2.view.clicks
import com.takhyungmin.dowadog.BaseActivity
import com.takhyungmin.dowadog.R
import kotlinx.android.synthetic.main.activity_donation.*

class DonationActivity : BaseActivity() {

    lateinit var donationRecyclerViewAdatper : DoantionRecyclerViewAdpater


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donation)
        init()
        setOnClickListener()
    }

    fun init(){
        // 백버튼
        btn_back_donation_act.clicks().subscribe{
            finish()
        }

        rv_donation_act.setFocusable(false)
    }

    fun setOnClickListener(){

        var e = ArrayList<DoantionRecyclerViewAdpaterData>()
        e.add(DoantionRecyclerViewAdpaterData(R.drawable.fromella_img, "프롬엘라", "반려동물 사진 촬영 전문 스튜디오",
                "https://fromella-pet.com"))

        e.add(DoantionRecyclerViewAdpaterData(R.drawable.klorenz_img, "클로렌즈", "유기동물 보호소의 재정자립을 돕는 패션 브랜드",
                "http://bit.ly/2s0LM6s"))

        donationRecyclerViewAdatper = DoantionRecyclerViewAdpater(this@DonationActivity, e)
        rv_donation_act.adapter = donationRecyclerViewAdatper
        rv_donation_act.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.VERTICAL, false)
    }




}
