package com.takhyungmin.dowadog.contents.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.contents.ContentsObject
import com.takhyungmin.dowadog.contents.model.get.GetEduContentsContents

class ContentsSenseRvAdapter(var contentsSenseItems : ArrayList<GetEduContentsContents>, var requestManager: RequestManager) : RecyclerView.Adapter<ContentsSenseViewHolder>() {

    override fun onCreateViewHolder(parnet: ViewGroup, p1: Int): ContentsSenseViewHolder {
        val mainView = LayoutInflater.from(parnet.context).inflate(R.layout.fragment_contents_sense_item, parnet,false)
        //mainView.setOnClickListener(onItemClick)
        return ContentsSenseViewHolder(mainView)
    }

    override fun getItemCount(): Int = contentsSenseItems.size

    override fun onBindViewHolder(holder: ContentsSenseViewHolder, position: Int) {
        holder.senseTitle.text = contentsSenseItems[position].title
        holder.senseSub.text = contentsSenseItems[position].subtitle
        requestManager.load(contentsSenseItems[position].imgPath).into(holder.senseFeed)
        holder.senseFrame.setOnClickListener {
            ContentsObject.contentsSenseFragmentPresenter.toDetail(contentsSenseItems[position].id, contentsSenseItems[position].imgPath)
        }
        holder.senseFeed.setClipToOutline(true)
    }
}