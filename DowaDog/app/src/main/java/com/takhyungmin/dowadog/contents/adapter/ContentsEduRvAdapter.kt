package com.takhyungmin.dowadog.contents.adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.contents.ContentsObject
import com.takhyungmin.dowadog.contents.model.get.GetEduContentsContents


class ContentsEduRvAdapter(private var contentsEduItems : ArrayList<GetEduContentsContents>, private var requestManager: RequestManager) : RecyclerView.Adapter<ContentsEduViewHolder>() {

    private var onItemClick : View.OnClickListener? = null
    lateinit var mainView : View
    var mRecyclerView: RecyclerView? = null

    override fun onCreateViewHolder(parnet: ViewGroup, p1: Int): ContentsEduViewHolder {
        mainView = LayoutInflater.from(parnet.context).inflate(R.layout.fragment_contents_edu_item, parnet,false)
        //mainView.setOnClickListener(onItemClick)
        return ContentsEduViewHolder(mainView)
    }

    override fun getItemCount(): Int = contentsEduItems.size

    override fun onBindViewHolder(holder: ContentsEduViewHolder, position: Int) {
        holder.contentsTitle.text = contentsEduItems[position].title
        holder.contentsSub.text = contentsEduItems[position].subtitle
        requestManager.load(contentsEduItems[position].imgPath).into(holder.contentsFeed)
        if (contentsEduItems[position].educated)
            holder.contentsCheck.setBackgroundColor(Color.parseColor("#ffc233"))
        else
            holder.contentsCheck.setBackgroundColor(Color.parseColor("#f0f0f0"))
        holder.contentsFrame.setOnClickListener {
            var num = 0
            contentsEduItems.forEach { it->
                if(it.educated)
                    num++
            }
            ContentsObject.contentsEduFragmentPresenter.toDetail(contentsEduItems[position].id, num)
        }

        holder.contentsFeed.setClipToOutline(true)
    }

    fun setOnItemClickListener(l:View.OnClickListener){
        onItemClick = l
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        mRecyclerView = recyclerView
    }

    fun getViewAtIndex(recycler: RecyclerView, index: Int): View? {
//        if (index >= 0) {
//
//            for (i in 0 until contentsEduItems.size) {
//
//                val pos = mainView.getChildAdapterPosition(child)
//
//                if (pos == index) {
//                    return child
//                }
//            }
//        }
        Log.v("리사이클러", mRecyclerView!!.toString())
        return mRecyclerView!!.getChildAt(index)


        // There is no view for this index - it is offscreen
    }
}