package com.takhyungmin.dowadog.contents.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.takhyungmin.dowadog.R

class ContentsSenseViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    var senseFeed = itemView.findViewById(R.id.img_contents_sense_item_feed) as ImageView
    var senseTitle = itemView.findViewById(R.id.tv_contents_sense_item_title) as TextView
    var senseSub = itemView.findViewById(R.id.tv_contents_sense_item_sub) as TextView
    var senseFrame = itemView.findViewById(R.id.layout_contents_sense_frame) as LinearLayout
}