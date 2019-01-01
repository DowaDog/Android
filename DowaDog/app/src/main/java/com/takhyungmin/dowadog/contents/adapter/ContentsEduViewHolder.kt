package com.takhyungmin.dowadog.contents.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.takhyungmin.dowadog.R

class ContentsEduViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    var contentsFeed = itemView.findViewById(R.id.img_contents_edu_item_feed) as ImageView
    var contentsRead = itemView.findViewById(R.id.img_contents_edu_item_read) as ImageView
    var contentsTitle = itemView.findViewById(R.id.tv_contents_edu_item_title) as TextView
    var contentsSub = itemView.findViewById(R.id.tv_contents_edu_item_sub) as TextView
    var contentsFrame = itemView.findViewById(R.id.layout_contents_edu_frame) as LinearLayout
    var contentsCheck = itemView.findViewById(R.id.layout_contents_edu_item_frame) as RelativeLayout

}