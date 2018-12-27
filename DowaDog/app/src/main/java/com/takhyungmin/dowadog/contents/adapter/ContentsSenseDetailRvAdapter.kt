package com.takhyungmin.dowadog.contents.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.takhyungmin.dowadog.R

class ContentsSenseDetailRvAdapter(var contentsSenseDetailItems : ArrayList<ContentsSenseDetailItem>,
                                 var requestManager: RequestManager) : RecyclerView.Adapter<ContentsSenseDetailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ContentsSenseDetailViewHolder {
        val mainView = LayoutInflater.from(parent.context).inflate(R.layout.activity_contents_sense_detail_item, parent,false)
        //mainView.setOnClickListener(onItemClick)
        return ContentsSenseDetailViewHolder(mainView)

    }

    override fun getItemCount(): Int = contentsSenseDetailItems.size

    override fun onBindViewHolder(holder: ContentsSenseDetailViewHolder, position: Int) {
        holder.contentsSenseDtailSubTitle.text = contentsSenseDetailItems[position].contentsSenseDetailSubTitle
        holder.contentsSenseDetailContent.text = contentsSenseDetailItems[position].contentsSenseDetailContent
        requestManager.load(contentsSenseDetailItems[position].contentsSenseDetailImage).into(holder.contentsSenseDetailImage)
    }
}