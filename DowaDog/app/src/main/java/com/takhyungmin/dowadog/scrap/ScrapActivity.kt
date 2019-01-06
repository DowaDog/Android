package com.takhyungmin.dowadog.scrap

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.takhyungmin.dowadog.BaseActivity
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.presenter.activity.ScrapActivityPresenter
import com.takhyungmin.dowadog.scrap.scrapmodel.ScrapObject
import com.takhyungmin.dowadog.scrap.scrapmodel.get.GetMyScrapData
import com.takhyungmin.dowadog.scrap.scrapmodel.get.GetMyScrapResponse
import kotlinx.android.synthetic.main.activity_scrap.*

class ScrapActivity : BaseActivity(), View.OnClickListener {

    lateinit var myScrapList: GetMyScrapResponse

    lateinit var scrapActivityPresenter: ScrapActivityPresenter


    override fun onClick(v: View?) {
        when (v) {
            btn_back_scrap_act -> {
                finish()
            }
        }
    }

    lateinit var scrapRecyclerViewAdapter: ScrapRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrap)
        initPresenter()
        scrapActivityPresenter.requestMyScrapList()
        init()

        initView()
    }

    private fun init() {
        btn_back_scrap_act.setOnClickListener(this)
    }

    private fun initView() {
    }

    private fun setRVListener(data : ArrayList<GetMyScrapData>) {
//        dataList = ArrayList()
//        dataList.add(ScrapRVData(1, "작성자 이름", "2018.12.12"))
//        dataList.add(ScrapRVData(1, "작성자 이름", "2018.12.12"))
//        dataList.add(ScrapRVData(1, "작성자 이름", "2018.12.12"))
//        dataList.add(ScrapRVData(1, "작성자 이름", "2018.12.12"))
//        dataList.add(ScrapRVData(1, "작성자 이름", "2018.12.12"))
//        dataList.add(ScrapRVData(1, "작성자 이름", "2018.12.12"))
//        dataList.add(ScrapRVData(1, "작성자 이름", "2018.12.12"))
        scrapRecyclerViewAdapter = ScrapRecyclerViewAdapter(this@ScrapActivity, data)
        rv_scrap_act.adapter = scrapRecyclerViewAdapter
        rv_scrap_act.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.VERTICAL, false)
    }

    private fun initPresenter() {
        scrapActivityPresenter = ScrapActivityPresenter()
        // 뷰 붙여주는 작엄
        scrapActivityPresenter.view = this
        ScrapObject.scrapActivityPresenter = scrapActivityPresenter
    }

    fun responseScrapData(data: GetMyScrapResponse) {
        myScrapList = data
        myScrapList?.data?.let {
            setRVListener(it)
        }

    }
}
