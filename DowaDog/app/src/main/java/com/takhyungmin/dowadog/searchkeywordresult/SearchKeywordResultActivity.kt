package com.takhyungmin.dowadog.searchkeywordresult

import android.os.Bundle
import android.os.Handler
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.takhyungmin.dowadog.BaseActivity
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.presenter.activity.SearchKeywordResultActivityPresenter
import com.takhyungmin.dowadog.searchresult.SearchResultObject
import com.takhyungmin.dowadog.searchresult.adapter.SearchResultAdapter
import com.takhyungmin.dowadog.searchresult.model.ggg.Content
import com.takhyungmin.dowadog.searchresult.model.ggg.ccc
import kotlinx.android.synthetic.main.activity_search_result.*

class SearchKeywordResultActivity : BaseActivity(), View.OnClickListener {

    var isLoading = false

    var currentPage = 0

    var TOTAL_PAGE = 10

    var isLast = false

    lateinit var keyword : String

    lateinit var searchFilterResultResponse: ccc

    private lateinit var searchKeywordResultActivityPresenter: SearchKeywordResultActivityPresenter

    lateinit var searchResultAdapter : SearchResultAdapter

    lateinit var requestManager: RequestManager

    override fun onClick(v: View?) {
        when (v) {
            btn_back_search_result_act -> {
                finish()
            }
        }
    }

//    private fun getIntentData(){
//        keyword = intent.getStringExtra("keyword")
//        Log.v("SearchResultActivity", keyword)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // getIntentData()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)
        keyword = intent.getStringExtra("keyword")
        init()
        initPresenter()

        requestResultData(0, 10, keyword)
    }

    private fun init() {
        tv_keyword_search_act.text = "\'" + keyword + "\' " + "검색결과"
        tv_temp_search_act.text = ""
        btn_back_search_result_act.setOnClickListener(this)
        nested_scroll_search_result_act.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
            Log.v("scroll", "scroll")
            if (scrollY == ( v.getChildAt(0).height - v.height )) {
                //scroll in bottom
                Log.v("scroll", "bottom")
                if (!isLoading and !isLast) {
                    //isLast = 스크롤의 마지막이 아니라 전체 아이템 중 마지
                    isLoading = true
                    Log.v("scroll", currentPage.toString())
                    currentPage++
                    Handler().postDelayed(Runnable {
                        //communityFragmentPresenter.nextPage(currentPage, itemCount)
                        Log.v("scroll", "more")
                        searchKeywordResultActivityPresenter.responseData(currentPage, 10, keyword)
                    }, 800)
                }
            }

        })
    }

    private fun setRVAdapter(dataList: ArrayList<Content>) {
        //animalItem.add(UrgentAnimalData("D-3","", "","","[인천] 러시안 블루" ))

        requestManager = Glide.with(this)
        searchResultAdapter = SearchResultAdapter(this, dataList, requestManager)
        rv_search_result_act.adapter = searchResultAdapter
        rv_search_result_act.layoutManager = GridLayoutManager(this, 2)
    }

    fun requestResultData(page: Int, limit: Int, keyword: String) {
        searchKeywordResultActivityPresenter.responseData(page, limit, keyword)
    }

    fun responseData(data: ccc) {
        data?.let {
            searchFilterResultResponse = data
            Log.v("TAGGG", searchFilterResultResponse.toString())
            setRVAdapter(searchFilterResultResponse.data.content)
            if(data.data.content.size<= 0){
                rl_no_search_result_act.visibility = View.VISIBLE
                nested_scroll_search_result_act.visibility = View.GONE
            }else {
                rl_no_search_result_act.visibility = View.GONE
                nested_scroll_search_result_act.visibility = View.VISIBLE
            }

        }
    }

    private fun initPresenter() {
        searchKeywordResultActivityPresenter = SearchKeywordResultActivityPresenter()
        // 뷰 붙여주는 작엄
        searchKeywordResultActivityPresenter.view = this
        SearchResultObject.searchKeywordResultActivityPresenter = searchKeywordResultActivityPresenter
    }

    fun loadNextPage(results : ArrayList<Content>){
        Log.v("scroll", "add")
        searchResultAdapter.addAll(results)
        //currentPage += 1
        isLoading = false
        if (currentPage >= TOTAL_PAGE)
            isLast = true
    }




}