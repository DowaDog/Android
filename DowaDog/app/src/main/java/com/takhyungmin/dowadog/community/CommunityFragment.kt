package com.takhyungmin.dowadog.community

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.jakewharton.rxbinding2.view.clicks
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.community.adapter.CommunityAdapter
import com.takhyungmin.dowadog.community.model.get.GetCommunityContents
import com.takhyungmin.dowadog.communitydetail.CommunityDetailActivity
import com.takhyungmin.dowadog.communitywrite.CommunityWriteActivity
import com.takhyungmin.dowadog.presenter.fragment.CommunityFragmentPresenter
import kotlinx.android.synthetic.main.fragment_community.*
import org.jetbrains.anko.support.v4.startActivity


class CommunityFragment : Fragment() {

    lateinit var communityFragmentPresenter : CommunityFragmentPresenter
    lateinit var requestManager: RequestManager
    lateinit var communityAdapter : CommunityAdapter
    lateinit var communityItems : ArrayList<GetCommunityContents>
    var TOTAL_PAGE = 10
    //서버에서 받아올 수 있나..?
    var isLoading = false
    var isLast = false
    var currentPage = 0
    var startPage = 0
    val itemCount = 5
    val pagingCount = 16

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_community, container, false)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        communityFragmentPresenter = CommunityFragmentPresenter()
        communityFragmentPresenter.view = this
        CommunityObject.communityFragmentPresenter = communityFragmentPresenter
        requestManager = Glide.with(this)
    }

    override fun onStart() {
        super.onStart()
        setOnBinding()
        communityFragmentPresenter.initView(0, pagingCount)
    }

    fun initView(communityItems : ArrayList<GetCommunityContents>){
        this.communityItems = communityItems
        communityAdapter = CommunityAdapter(communityItems, requestManager, communityFragmentPresenter, context!!)
        rv_community_feeds.layoutManager = LinearLayoutManager(activity)
        rv_community_feeds.adapter = communityAdapter


        scroll_comunity_frame.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
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
                        communityFragmentPresenter.requestCommunityList(currentPage, pagingCount)
                    }, 2000)
                }
            }

        })
    }

    fun toDetail(communityId: Int){
        startActivity<CommunityDetailActivity>("communityId" to communityId)
    }

    fun setOnBinding(){
        btn_community_write.clicks().subscribe {
            startActivity(Intent(activity, CommunityWriteActivity::class.java))
        }
    }

    fun loadNextPage(results : ArrayList<GetCommunityContents>){
        Log.v("scroll", "add")
        communityAdapter.addAll(results)
        //currentPage += 1
        isLoading = false
        if (pagingCount > results.size)
            isLast = true
    }
}
