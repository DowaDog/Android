package com.takhyungmin.dowadog.contents.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.takhyungmin.dowadog.R

class ContentsEduDetailRvAdapter(var contentsEduDetailItems : ArrayList<ContentsEduDetailItem       >,
                                 var requestManager: RequestManager) : RecyclerView.Adapter<ContentsEduDetailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ContentsEduDetailViewHolder {
        val mainView = LayoutInflater.from(parent.context).inflate(R.layout.activity_contents_edu_detail_item, parent,false)
        //mainView.setOnClickListener(onItemClick)
        return ContentsEduDetailViewHolder(mainView)

    }

    override fun getItemCount(): Int = contentsEduDetailItems.size

    override fun onBindViewHolder(holder: ContentsEduDetailViewHolder, position: Int) {
        holder.contentsEduDtailSubTitle.text = contentsEduDetailItems[position].contentsEduDetailSubTitle
        holder.contentsEduDetailContent.text = contentsEduDetailItems[position].contentsEduDetailContent
        requestManager.load(contentsEduDetailItems[position].contentsEduDetailImage).into(holder.contentsEduDetailImage)
    }
}