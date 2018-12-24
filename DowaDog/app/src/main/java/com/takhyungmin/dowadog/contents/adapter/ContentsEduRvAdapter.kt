package com.takhyungmin.dowadog.contents.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.presenter.fragment.ContentsEduFragmentPresenter

class ContentsEduRvAdapter(var contentsEduItems : ArrayList<ContentsEduItem>, var requestManager: RequestManager, var contentsEduFragmentPresenter : ContentsEduFragmentPresenter) : RecyclerView.Adapter<ContentsEduViewHolder>() {
    override fun onCreateViewHolder(parnet: ViewGroup, p1: Int): ContentsEduViewHolder {
        val mainView : View = LayoutInflater.from(parnet.context).inflate(R.layout.fragment_contents_edu_item, parnet,false)

        return ContentsEduViewHolder(mainView)
    }

    override fun getItemCount(): Int = contentsEduItems.size

    override fun onBindViewHolder(holder: ContentsEduViewHolder, position: Int) {
        holder.contentsTitle.text = contentsEduItems[position].contentsTitle
        holder.contentsSub.text = contentsEduItems[position].contentsSub
        requestManager.load(contentsEduItems[position].contentsFeed).into(holder.contentsFeed)
        if (contentsEduItems[position].contentsRead)
            holder.contentsRead.setBackgroundColor(R.drawable.drawer_btn)

        holder.contentsFrame.setOnClickListener {
//            val screenLocation = IntArray(2)
//            holder.contentsFrame.getLocationOnScreen(screenLocation)
//            val width = holder.contentsFrame.width
//            val height = holder.contentsFrame.height
//            val title = contentsEduItems[position].contentsTitle
//            val sub = contentsEduItems[position].contentsSub
//            val left = screenLocation[0]
//            val top = screenLocation[1]
            contentsEduFragmentPresenter.toDetail()
        }
    }
}