package com.takhyungmin.dowadog.communitydetail

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.takhyungmin.dowadog.R


class CommunityDetailRecyclerViewAdapter(var ctx: Context, var dataList: ArrayList<CommunityCommentData>) : RecyclerView.Adapter<CommunityDetailRecyclerViewAdapter.CommunityDetailRecyclerViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CommunityDetailRecyclerViewHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.rv_item_comment_community_detail_act, p0, false)
        return CommunityDetailRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: CommunityDetailRecyclerViewHolder, position: Int) {


        Glide.with(ctx).load(dataList[position].img).into(holder.writerIV)

        holder.dataTV.text = dataList[position].date

    }

    inner class CommunityDetailRecyclerViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {


        var writerIV : ImageView = itemView!!.findViewById(R.id.iv_writter_rv_item_comment_community_detail_act)
        var contentTV : TextView = itemView!!.findViewById(R.id.tv_content_rv_item_comment_community_detail_act1)
        var dataTV: TextView = itemView!!.findViewById(R.id.tv_data_rv_item_comment_community_detail_act)

    }
}

data class CommunityCommentData(


        var img : String,
        var writter : String,
        var description : String,
        var date: String
)