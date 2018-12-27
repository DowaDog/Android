package com.takhyungmin.dowadog.community.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.takhyungmin.dowadog.R

class CommunityViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    var communityProfile = itemView.findViewById(R.id.img_community_item_profile) as ImageView
    var communityName = itemView.findViewById(R.id.tv_community_item_name) as TextView
    var communityTime = itemView.findViewById(R.id.tv_community_item_time) as TextView

    var communityMain1 = itemView.findViewById(R.id.img_community_item_main1) as ImageView
    var communityMain2 = itemView.findViewById(R.id.img_community_item_main2) as LinearLayout
    var communityMain3 = itemView.findViewById(R.id.img_community_item_main3) as LinearLayout
    var communityMain4 = itemView.findViewById(R.id.img_community_item_main4) as RelativeLayout

    var communityMain21 = itemView.findViewById(R.id.img_community_item_main21) as ImageView
    var communityMain22 = itemView.findViewById(R.id.img_community_item_main22) as ImageView
    var communityMain31 = itemView.findViewById(R.id.img_community_item_main31) as ImageView
    var communityMain32 = itemView.findViewById(R.id.img_community_item_main32) as ImageView
    var communityMain33 = itemView.findViewById(R.id.img_community_item_main33) as ImageView

}