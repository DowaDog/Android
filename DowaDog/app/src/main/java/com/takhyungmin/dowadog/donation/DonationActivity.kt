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
    }

    fun setOnClickListener(){

        var e = ArrayList<DoantionRecyclerViewAdpaterData>()
        e.add(DoantionRecyclerViewAdpaterData("https://ryudd.s3.amazonaws.com/dowadog/myinfo/7ca5021b734043d1ac2eb15280c197aa사용자_프로필.png", "신발 엄청 큰거 신고다니는 애", "신발 엄청 큰거 신고다니는 애",
                "http://bit.ly/2s0LM6s "))

        e.add(DoantionRecyclerViewAdpaterData("https://ryudd.s3.amazonaws.com/dowadog/myinfo/7ca5021b734043d1ac2eb15280c197aa사용자_프로필.png", "신발 엄청 큰거 신고다니는 애", "신발 엄청 큰거 신고다니는 애",
                "http://bit.ly/2s0LM6s "))

        e.add(DoantionRecyclerViewAdpaterData("dowadog/myinfo/7ca5021b734043d1ac2eb15280c197aa사용자_프로필.png", "신발 엄청 큰거 신고다니는 애", "신발 엄청 큰거 신고다니는 애",
                "http://bit.ly/2s0LM6s "))

        e.add(DoantionRecyclerViewAdpaterData("dowadog/myinfo/7ca5021b734043d1ac2eb15280c197aa사용자_프로필.png", "신발 엄청 큰거 신고다니는 애", "신발 엄청 큰거 신고다니는 애",
                "http://bit.ly/2s0LM6s "))

        e.add(DoantionRecyclerViewAdpaterData("dowadog/myinfo/7ca5021b734043d1ac2eb15280c197aa사용자_프로필.png", "신발 엄청 큰거 신고다니는 애", "신발 엄청 큰거 신고다니는 애",
                "http://bit.ly/2s0LM6s "))

        e.add(DoantionRecyclerViewAdpaterData("dowadog/myinfo/7ca5021b734043d1ac2eb15280c197aa사용자_프로필.png", "신발 엄청 큰거 신고다니는 애", "신발 엄청 큰거 신고다니는 애",
                "http://bit.ly/2s0LM6s "))

        e.add(DoantionRecyclerViewAdpaterData("dowadog/myinfo/7ca5021b734043d1ac2eb15280c197aa사용자_프로필.png", "신발 엄청 큰거 신고다니는 애", "신발 엄청 큰거 신고다니는 애",
                "http://bit.ly/2s0LM6s "))

        e.add(DoantionRecyclerViewAdpaterData("dowadog/myinfo/7ca5021b734043d1ac2eb15280c197aa사용자_프로필.png", "신발 엄청 큰거 신고다니는 애", "신발 엄청 큰거 신고다니는 애",
                "http://bit.ly/2s0LM6s "))

        e.add(DoantionRecyclerViewAdpaterData("dowadog/myinfo/7ca5021b734043d1ac2eb15280c197aa사용자_프로필.png", "신발 엄청 큰거 신고다니는 애", "신발 엄청 큰거 신고다니는 애",
                "http://bit.ly/2s0LM6s "))
        e.add(DoantionRecyclerViewAdpaterData("dowadog/myinfo/7ca5021b734043d1ac2eb15280c197aa사용자_프로필.png", "신발 엄청 큰거 신고다니는 애", "신발 엄청 큰거 신고다니는 애",
                "http://bit.ly/2s0LM6s "))

        e.add(DoantionRecyclerViewAdpaterData("dowadog/myinfo/7ca5021b734043d1ac2eb15280c197aa사용자_프로필.png", "신발 엄청 큰거 신고다니는 애", "신발 엄청 큰거 신고다니는 애",
                "http://bit.ly/2s0LM6s "))


        donationRecyclerViewAdatper = DoantionRecyclerViewAdpater(this@DonationActivity, e)
        rv_donation_act.adapter = donationRecyclerViewAdatper
        rv_donation_act.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.VERTICAL, false)
    }




}
