package com.takhyungmin.dowadog.community

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.jakewharton.rxbinding2.view.clicks
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.community.adapter.CommunityAdapter
import com.takhyungmin.dowadog.community.model.CommunityItem
import com.takhyungmin.dowadog.communitydetail.CommunityDetailActivity
import com.takhyungmin.dowadog.communitywrite.CommunityWriteActivity
import com.takhyungmin.dowadog.presenter.fragment.CommunityFragmentPresenter
import kotlinx.android.synthetic.main.fragment_community.*



class CommunityFragment : Fragment() {

    lateinit var communityFragmentPresenter : CommunityFragmentPresenter
    lateinit var requestManager: RequestManager
    lateinit var communityAdapter : CommunityAdapter
    lateinit var communityItems : ArrayList<CommunityItem>
    var TOTAL_PAGE = 10
    //서버에서 받아올 수 있나..?
    var isLoading = false
    var isLast = false
    var currentPage = 0
    var startPage = 0
    val itemCount = 5

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_community, container, false)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        communityFragmentPresenter = CommunityFragmentPresenter()
        communityFragmentPresenter.view = this
        requestManager = Glide.with(this)
    }

    override fun onStart() {
        super.onStart()
        communityFragmentPresenter.initView()
    }

    fun initView(communityItems : ArrayList<CommunityItem>){
        this.communityItems = communityItems
        communityAdapter = CommunityAdapter(communityItems, requestManager, communityFragmentPresenter, context!!)
        //contentsEduRvAdapter.setOnItemClickListener(this)
        rv_community_feeds.layoutManager = LinearLayoutManager(activity)
        rv_community_feeds.adapter = communityAdapter

        rv_community_feeds.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Log.v("isLoading", isLoading.toString())
                Log.v("isLast", isLast.toString())
                Log.v("currentPage", currentPage.toString())
                Log.v("TOTAL_PAGE", TOTAL_PAGE.toString())

                if(!rv_community_feeds.canScrollVertically(-1)) {
                    // 맨 위
                }
                if(!rv_community_feeds.canScrollVertically(1)) {
                    // 맨 아래
                    if (!isLoading and !isLast) {
                        Log.v("continue", "continue")
                        isLoading = true
                        currentPage += 1
                        Log.v("currentPage", currentPage.toString())

                        Handler().postDelayed(Runnable {
                            communityFragmentPresenter.nextPage(currentPage, itemCount)
                        }, 2000)
                    }
                }
            }

        })
    }

    fun toDetail(){
        startActivity(Intent(activity, CommunityDetailActivity::class.java))
    }

    fun setOnBinding(){
        btn_community_write.clicks().subscribe {
            startActivity(Intent(activity, CommunityWriteActivity::class.java))
        }
    }

    fun loadNextPage(results : ArrayList<CommunityItem>){
        communityAdapter.addAll(results)
        isLoading = false
        if (currentPage > TOTAL_PAGE)
            isLast = true
    }

    fun loadFristPage(results : ArrayList<CommunityItem>){

        communityAdapter.addAll(results)


    }
}