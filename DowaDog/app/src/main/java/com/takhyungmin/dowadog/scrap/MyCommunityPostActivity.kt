package com.takhyungmin.dowadog.scrap

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.takhyungmin.dowadog.BaseActivity
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.presenter.activity.MyCommunityPostActivityPresenter
import com.takhyungmin.dowadog.scrap.model.MyCommunityPostObject
import com.takhyungmin.dowadog.scrap.model.get.GetMyCommunityPostResponse
import kotlinx.android.synthetic.main.activity_scrap.*

class MyCommunityPostActivity: BaseActivity(), View.OnClickListener {

    lateinit var myCommunityPostList : GetMyCommunityPostResponse

    lateinit var myCommunityPostActivityPresenter : MyCommunityPostActivityPresenter


    override fun onClick(v: View?) {
        when (v) {
            btn_back_scrap_act -> {
                finish()
            }
        }
    }

    val myCommunityPostRecyclerVIewAdapter: MyCommunityPostRecyclerVIewAdapter by lazy {
        MyCommunityPostRecyclerVIewAdapter(this@MyCommunityPostActivity, myCommunityPostList.data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrap)
        initPresenter()
        myCommunityPostActivityPresenter.requestMyCommunityPostList()
        init()
        initView()
        tv_scrap_act.text = "내가 쓴글"

    }

    private fun init() {
        btn_back_scrap_act.setOnClickListener(this)
    }

    private fun initView() {
    }

    private fun setRVListener() {
            rv_scrap_act.adapter = myCommunityPostRecyclerVIewAdapter
            rv_scrap_act.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.VERTICAL, false)
    }

    fun requestMyCommunityPostList(data : GetMyCommunityPostResponse){
        myCommunityPostList = data
        data?.data
                ?.let {
            setRVListener()
        }

    }

    private fun initPresenter() {
        myCommunityPostActivityPresenter = MyCommunityPostActivityPresenter()
        // 뷰 붙여주는 작엄
        myCommunityPostActivityPresenter.view = this
        MyCommunityPostObject.myCommunityPostActivityPresenter= myCommunityPostActivityPresenter
    }
}