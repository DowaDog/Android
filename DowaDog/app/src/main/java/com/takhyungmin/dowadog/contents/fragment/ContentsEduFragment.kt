package com.takhyungmin.dowadog.contents.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.contents.ContentsObject
import com.takhyungmin.dowadog.contents.activity.ContentsEduDetailActivity
import com.takhyungmin.dowadog.contents.adapter.ContentsEduRvAdapter
import com.takhyungmin.dowadog.contents.model.get.GetEduContentsContents
import com.takhyungmin.dowadog.presenter.fragment.ContentsEduFragmentPresenter
import kotlinx.android.synthetic.main.fragment_contents_edu.*


class ContentsEduFragment : Fragment(){

    lateinit var contentsEduFragmentPresenter : ContentsEduFragmentPresenter
    lateinit var requestManager: RequestManager
    lateinit var contentsEduRvAdapter: ContentsEduRvAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contents_edu, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.v("들어는 오나", "들어는 오나")
        //ActivityCompat.setExitSharedElementCallback(activity!!, ExitTransitionCallback)
        super.onCreate(savedInstanceState)
        contentsEduFragmentPresenter = ContentsEduFragmentPresenter()
        contentsEduFragmentPresenter.view = this
        ContentsObject.contentsEduFragmentPresenter = contentsEduFragmentPresenter
        requestManager = Glide.with(this)
    }

    override fun onStart() {
        super.onStart()
        contentsEduFragmentPresenter.requestData()
    }

    var num = 0
    fun toDetail(id : Int, num : Int){
        val intent = Intent(context, ContentsEduDetailActivity::class.java)
        intent.putExtra("id", id)
        intent.putExtra("num", num)
        activity!!.startActivity(intent)
    }

    fun responseList(contents : ArrayList<GetEduContentsContents>){
        contentsEduRvAdapter = ContentsEduRvAdapter(contents, requestManager)
        rv_contents_edu_feeds.layoutManager = LinearLayoutManager(activity)
        rv_contents_edu_feeds.adapter = contentsEduRvAdapter
    }
}