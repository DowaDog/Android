package com.takhyungmin.dowadog.contents.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.contents.activity.ContentsEduDetailActivity
import com.takhyungmin.dowadog.contents.adapter.ContentsEduItem
import com.takhyungmin.dowadog.contents.adapter.ContentsEduRvAdapter
import com.takhyungmin.dowadog.presenter.fragment.ContentsEduFragmentPresenter
import kotlinx.android.synthetic.main.fragment_contents_edu.*



class ContentsEduFragment : Fragment() {

    lateinit var contentsEduFragmentPresenter : ContentsEduFragmentPresenter
    lateinit var requestManager: RequestManager
    lateinit var contentsEduRvAdapter: ContentsEduRvAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contents_edu, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentsEduFragmentPresenter = ContentsEduFragmentPresenter()
        contentsEduFragmentPresenter.view = this
        requestManager = Glide.with(this)
    }

    override fun onStart() {
        super.onStart()
        contentsEduFragmentPresenter.initView()
    }

    fun toDetail(){
        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity!!,
                android.support.v4.util.Pair<View, String>(view!!.findViewById(R.id.img_contents_edu_item_feed),
                        ContentsEduDetailActivity.VIEW_NAME_HEADER_IMAGE),
                android.support.v4.util.Pair<View, String>(view!!.findViewById(R.id.tv_contents_edu_item_title),
                        ContentsEduDetailActivity.VIEW_NAME_HEADER_TITLE))


        val subActivity = Intent(activity,
                ContentsEduDetailActivity::class.java)
        //ActivityCompat.startActivity(activity!!, subActivity, )
        activity!!.startActivity(subActivity, activityOptions.toBundle())

        //activity!!.overridePendingTransition(0, 0)
    }

    fun initView(contentsItems : ArrayList<ContentsEduItem>){
        contentsEduRvAdapter = ContentsEduRvAdapter(contentsItems, requestManager, contentsEduFragmentPresenter)
        rv_contents_edu_feeds.layoutManager = LinearLayoutManager(activity)
        rv_contents_edu_feeds.adapter = contentsEduRvAdapter
    }
}