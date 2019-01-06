package com.takhyungmin.dowadog.searchresult

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
import com.takhyungmin.dowadog.presenter.activity.SearchResultActivityPresenter
import com.takhyungmin.dowadog.searchresult.adapter.SearchResultAdapter
import com.takhyungmin.dowadog.searchresult.model.ggg.Content
import com.takhyungmin.dowadog.searchresult.model.ggg.ccc
import kotlinx.android.synthetic.main.activity_search_result.*

class SearchResultActivity : BaseActivity(), View.OnClickListener {

    var isLoading = false

    var currentPage = 0

    var TOTAL_PAGE = 10

    var isLast = false

    var type = ""

    var region = ""

    var remainDate : Int = 300



    lateinit var searchFilterResultResponse: ccc

    private lateinit var searchResultActivityPresenter: SearchResultActivityPresenter

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

        var isDog = intent.getIntExtra("isDog", 9999)
        var isCat = intent.getIntExtra("isCat", 9999)
        var areaNum = intent.getIntExtra("areaNum", 9999)
        remainDate = intent.getIntExtra("remainDate", 9999)
        if (remainDate >= 15 ){
            remainDate = 300
        }

        init()
        initPresenter()

        /*전체 지역 = 0
                서울 = 1
                경기 = 2
                인천 = 3
                강원 = 4
                충청 = 5
                전라 = 6
                경상 = 7
                제주 = 8*/

        if (isDog == 0 && isCat == 0) {
            type = ""
            when (areaNum) {
                0 -> {
                    requestResultData("기타", "", remainDate, 0, 10)
                    region = ""
                }
                1 -> {
                    requestResultData("기타", "서울", remainDate, 0, 10)
                    region = "서울"
                }
                2 -> {
                    requestResultData("기타", "경기", remainDate, 0, 10)
                    region = "경기"
                }
                3 -> {
                    requestResultData("기타", "인천", remainDate, 0, 10)
                    region = "인천"
                }
                4 -> {
                    requestResultData("기타", "강원", remainDate, 0, 10)
                    region = "강원"
                }
                5 -> {
                    requestResultData("기타", "충청", remainDate, 0, 10)
                    region = "충청"
                }
                6 -> {
                    requestResultData("기타", "전라", remainDate, 0, 10)
                    region = "전라"
                }
                7 -> {
                    requestResultData("기타", "경상", remainDate, 0, 10)
                    region = "경상"
                }
                8 -> {
                    requestResultData("기타", "제주", remainDate, 0, 10)
                    region = "제주"
                }
            }
        } else if (isDog == 1 && isCat == 0) {
            type = "개"
            when (areaNum) {
                0 -> {
                    requestResultData("개", "", remainDate, 0, 10)
                    region = ""
                }
                1 -> {
                    requestResultData("개", "서울", remainDate, 0, 10)
                    region = "서울"
                }
                2 -> {
                    requestResultData("개", "경기", remainDate, 0, 10)
                    region = "경기"
                }
                3 -> {
                    requestResultData("개", "인천", remainDate, 0, 10)
                    region = "인천"
                }
                4 -> {
                    requestResultData("개", "강원", remainDate, 0, 10)
                    region = "강원"
                }
                5 -> {
                    requestResultData("개", "충청", remainDate, 0, 10)
                    region = "충청"
                }
                6 -> {
                    requestResultData("개", "전라", remainDate, 0, 10)
                    region = "전라"
                }
                7 -> {
                    requestResultData("개", "경상", remainDate, 0, 10)
                    region = "경상"
                }
                8 -> {
                    requestResultData("개", "제주", remainDate, 0, 10)
                    region = "제주"
                }
            }
        } else if (isDog == 0 && isCat == 1) {
            type = "고양이"
            when (areaNum) {
                0 -> {
                    requestResultData("고양이", "", remainDate, 0, 10)
                    region = ""
                }
                1 -> {
                    requestResultData("고양이", "서울", remainDate, 0, 10)
                    region = "서울"
                }
                2 -> {
                    requestResultData("고양이", "경기", remainDate, 0, 10)
                    region = "경기"
                }
                3 -> {
                    requestResultData("고양이", "인천", remainDate, 0, 10)
                    region = "인천"
                }
                4 -> {
                    requestResultData("고양이", "강원", remainDate, 0, 10)
                    region = "강원"
                }
                5 -> {
                    requestResultData("고양이", "충청", remainDate, 0, 10)
                    region = "충청"
                }
                6 -> {
                    requestResultData("고양이", "전라", remainDate, 0, 10)
                    region = "전라"
                }
                7 -> {
                    requestResultData("고양이", "경상", remainDate, 0, 10)
                    region = "경상"
                }
                8 -> {
                    requestResultData("고양이", "제주", remainDate, 0, 10)
                    region = "제주"
                }
            }
        }
    }

    private fun init() {
        tv_keyword_search_act.text = "필터 검색 결과"
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
                        searchResultActivityPresenter.responseData(type, region, remainDate, currentPage, 10)
                    }, 2000)
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

    fun requestResultData(type: String, region: String, remainDate: Int, page: Int, limit: Int) {
        Log.v("TAGGG234", "type :" + type + "region :" + region + "remainDate :" +  remainDate + "page :" + page + "limit :" + limit)
        searchResultActivityPresenter.responseData(type, region, remainDate, page, limit)
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
        searchResultActivityPresenter = SearchResultActivityPresenter()
        // 뷰 붙여주는 작엄
        searchResultActivityPresenter.view = this
        SearchResultObject.searchResultActivityPresenter = searchResultActivityPresenter
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
