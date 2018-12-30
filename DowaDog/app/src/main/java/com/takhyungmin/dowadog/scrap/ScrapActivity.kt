package com.takhyungmin.dowadog.scrap

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.takhyungmin.dowadog.BaseActivity
import com.takhyungmin.dowadog.R
import kotlinx.android.synthetic.main.activity_scrap.*

class ScrapActivity : BaseActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v) {
            btn_back_scrap_act -> {
                finish()
            }
        }
    }

    private var isScrapAct = 0

    val scrapRecyclerViewAdapter: ScrapRecyclerViewAdapter by lazy{
        ScrapRecyclerViewAdapter(this@ScrapActivity, dataList, 1)
    }

    val fromMePostsRecyclerViewAdapter: ScrapRecyclerViewAdapter by lazy{
        ScrapRecyclerViewAdapter(this@ScrapActivity, dataList, 0)
    }

    lateinit var dataList : ArrayList<ScrapRVData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrap)
        setRVListener()
        init()

        initView()
    }

    private fun init(){
        btn_back_scrap_act.setOnClickListener(this)
    }
    private fun initView(){
        if(isScrapAct == 0){
            tv_scrap_act.text = "내가 쓴글"
        }
    }

    private fun setRVListener(){
        if(isScrapAct == 1){
            dataList = ArrayList()
            dataList.add(ScrapRVData(1, "작성자 이름", "2018.12.12"))
            dataList.add(ScrapRVData(1, "작성자 이름", "2018.12.12"))
            dataList.add(ScrapRVData(1, "작성자 이름", "2018.12.12"))
            dataList.add(ScrapRVData(1, "작성자 이름", "2018.12.12"))
            dataList.add(ScrapRVData(1, "작성자 이름", "2018.12.12"))
            dataList.add(ScrapRVData(1, "작성자 이름", "2018.12.12"))
            dataList.add(ScrapRVData(1, "작성자 이름", "2018.12.12"))
            rv_scrap_act.adapter = scrapRecyclerViewAdapter
            rv_scrap_act.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.VERTICAL, false)
        }
        else{

            dataList = ArrayList()
            dataList.add(ScrapRVData(1, "내 이름", "2018.12.12"))
            dataList.add(ScrapRVData(1, "작성자 이름", "2018.12.12"))
            dataList.add(ScrapRVData(1, "작성자 이름", "2018.12.12"))
            dataList.add(ScrapRVData(1, "작성자 이름", "2018.12.12"))
            dataList.add(ScrapRVData(1, "작성자 이름", "2018.12.12"))
            dataList.add(ScrapRVData(1, "작성자 이름", "2018.12.12"))
            dataList.add(ScrapRVData(1, "작성자 이름", "2018.12.12"))
            rv_scrap_act.adapter = fromMePostsRecyclerViewAdapter
            rv_scrap_act.layoutManager = LinearLayoutManager(applicationContext, LinearLayout.VERTICAL, false)

        }
    }
}
