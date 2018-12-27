package com.takhyungmin.dowadog.contents.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.presenter.fragment.ContentsSenseFragmentPresenter

class ContentsSenseRvAdapter(var contentsSenseItems : ArrayList<ContentsSenseItem>, var requestManager: RequestManager, var contentsSenseFragmentPresenter: ContentsSenseFragmentPresenter) : RecyclerView.Adapter<ContentsSenseViewHolder>() {

    override fun onCreateViewHolder(parnet: ViewGroup, p1: Int): ContentsSenseViewHolder {
        val mainView = LayoutInflater.from(parnet.context).inflate(R.layout.fragment_contents_sense_item, parnet,false)
        //mainView.setOnClickListener(onItemClick)
        return ContentsSenseViewHolder(mainView)
    }

    override fun getItemCount(): Int = contentsSenseItems.size

    override fun onBindViewHolder(holder: ContentsSenseViewHolder, position: Int) {
        holder.senseTitle.text = contentsSenseItems[position].contentsSenseTitle
        holder.senseSub.text = contentsSenseItems[position].contentsSenseSub
        requestManager.load(contentsSenseItems[position].contentsSenseFeed).into(holder.senseFeed)
        holder.senseFrame.setOnClickListener {
            contentsSenseFragmentPresenter.toDetail(contentsSenseItems[position].contentsSenseTitle,
                    contentsSenseItems[position].contentsSenseSub)
        }
    }
}