package com.takhyungmin.dowadog.contents.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.takhyungmin.dowadog.R

class ContentsEduDetailViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    var contentsEduDetailImage = itemView.findViewById(R.id.img_contents_edu_detail_item_image) as ImageView
    var contentsEduDtailSubTitle = itemView.findViewById(R.id.tv_contents_edu_detail_item_subtitle) as TextView
    var contentsEduDetailContent = itemView.findViewById(R.id.tv_contents_edu_detail_item_content) as TextView
}