package com.takhyungmin.dowadog.searchresult

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import com.takhyungmin.dowadog.BaseActivity
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.searchresult.adapter.SearchResultAdapter
import com.takhyungmin.dowadog.urgent.UrgentAnimalData
import kotlinx.android.synthetic.main.activity_search_result.*

class SearchResultActivity : BaseActivity(), View.OnClickListener {

    private var keyword : String = ""
    override fun onClick(v: View?) {
        when (v) {
            btn_back_search_result_act -> {
                finish()
            }
        }
    }

    private fun getIntentData(){
        keyword = intent.getStringExtra("keyword")
        Log.v("SearchResultActivity", keyword)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        getIntentData()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)
        init()
        setRVAdapter()
    }

    private fun init(){
        btn_back_search_result_act.setOnClickListener(this)
    }

    private fun setRVAdapter(){

        var animalItem: ArrayList<UrgentAnimalData> = ArrayList()

        animalItem.add(UrgentAnimalData("D-1","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","믹스견", "[충청]"))
        animalItem.add(UrgentAnimalData("D-2","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","페르시안", "[전라도] "))
        animalItem.add(UrgentAnimalData("D-1","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","믹스견", "[충청]"))
        animalItem.add(UrgentAnimalData("D-2","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","페르시안", "[전라도] "))
        animalItem.add(UrgentAnimalData("D-1","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","믹스견", "[충청]"))
        animalItem.add(UrgentAnimalData("D-2","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","페르시안", "[전라도] "))
        animalItem.add(UrgentAnimalData("D-1","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","믹스견", "[충청]"))
        animalItem.add(UrgentAnimalData("D-2","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","페르시안", "[전라도] "))
        animalItem.add(UrgentAnimalData("D-1","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","믹스견", "[충청]"))
        animalItem.add(UrgentAnimalData("D-2","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","페르시안", "[전라도] "))
        animalItem.add(UrgentAnimalData("D-1","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","믹스견", "[충청]"))

        //animalItem.add(UrgentAnimalData("D-3","", "","","[인천] 러시안 블루" ))

        var searchResultAdapter = SearchResultAdapter(this, animalItem)

        rv_search_result_act.adapter = searchResultAdapter
        rv_search_result_act.layoutManager = GridLayoutManager(this, 2)
    }
}
