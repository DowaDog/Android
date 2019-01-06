package com.takhyungmin.dowadog.contents.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.contents.ContentsObject
import com.takhyungmin.dowadog.contents.activity.ContentsSenseDetailActivity
import com.takhyungmin.dowadog.contents.adapter.ContentsSenseItem
import com.takhyungmin.dowadog.contents.adapter.ContentsSenseRvAdapter
import com.takhyungmin.dowadog.contents.model.get.GetEduContentsContents
import com.takhyungmin.dowadog.presenter.fragment.ContentsSenseFragmentPresenter
import kotlinx.android.synthetic.main.fragment_contents_sense.*

class ContentsSenseFragment : Fragment() {

    lateinit var contentsSenseFragmentPresenter : ContentsSenseFragmentPresenter
    lateinit var requestManager: RequestManager
    lateinit var contentsSenseRvAdapter: ContentsSenseRvAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contents_sense, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //ActivityCompat.setExitSharedElementCallback(activity!!, ExitTransitionCallback)
        super.onCreate(savedInstanceState)
        contentsSenseFragmentPresenter = ContentsSenseFragmentPresenter()
        contentsSenseFragmentPresenter.view = this
        ContentsObject.contentsSenseFragmentPresenter = contentsSenseFragmentPresenter
        requestManager = Glide.with(this)
    }

    override fun onStart() {
        super.onStart()
        contentsSenseFragmentPresenter.initView()
    }

    fun initView(contentsItems : ArrayList<ContentsSenseItem>){
//        contentsSenseRvAdapter = ContentsSenseRvAdapter(contentsItems, requestManager, contentsSenseFragmentPresenter)
//        //contentsEduRvAdapter.setOnItemClickListener(this)
//        rv_contents_sense_feeds.layoutManager = LinearLayoutManager(activity)
//        rv_contents_sense_feeds.adapter = contentsSenseRvAdapter
    }

    fun toDetail(id : Int){
        val intent = Intent(context, ContentsSenseDetailActivity::class.java)
        intent.putExtra("id", id)
        activity!!.startActivity(intent)
    }



    fun responseList(contents : ArrayList<GetEduContentsContents>){
        contentsSenseRvAdapter = ContentsSenseRvAdapter(contents, requestManager)
        //contentsEduRvAdapter.setOnItemClickListener(this)
        rv_contents_sense_feeds.layoutManager = LinearLayoutManager(activity)
        rv_contents_sense_feeds.adapter = contentsSenseRvAdapter
    }


}