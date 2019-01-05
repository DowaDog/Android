package com.takhyungmin.dowadog.adopt.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.takhyungmin.dowadog.R

class AnimalFindUrgentViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    var newFrame : LinearLayout = itemView.findViewById(R.id.rl_root_view_rv_item_urgent_anmal_act_box)
    var heart_touch : RelativeLayout = itemView.findViewById(R.id.rl_heart_touch_rv_item_urgent_ani_act)
    var heart : ImageView = itemView.findViewById(R.id.img_heart_rv_item_urgent_ani_act)

    val d_day : TextView = itemView.findViewById(R.id.tv_day_rv_item_urgent_ani_act) as TextView
    val ani_img : ImageView = itemView.findViewById(R.id.img_item_urgent_ani_act) as ImageView
    val tv_ani_kind : TextView = itemView.findViewById(R.id.tv_kind_dog_rv_item_urgent_ani_act) as TextView
    val ani_gender : ImageView = itemView.findViewById(R.id.img_gender_rv_item_urgent_ani_act) as ImageView
    val ani_region : TextView = itemView.findViewById(R.id.tv_region_rv_item_urgent_ani_act) as TextView
}