package com.takhyungmin.dowadog.contents.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.takhyungmin.dowadog.R

class ContentsSenseDetailViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    var contentsSenseDetailImage = itemView.findViewById(R.id.img_contents_sense_detail_item_image) as ImageView
    var contentsSenseDtailSubTitle = itemView.findViewById(R.id.tv_contents_sense_detail_item_subtitle) as TextView
    var contentsSenseDetailContent = itemView.findViewById(R.id.tv_contents_sense_detail_item_content) as TextView
}