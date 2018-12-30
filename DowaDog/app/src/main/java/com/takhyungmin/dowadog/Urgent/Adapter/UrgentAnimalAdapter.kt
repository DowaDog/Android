package com.takhyungmin.dowadog.urgent.Adapter

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.R.id.img_heart_rv_item_urgent_ani_act
import com.takhyungmin.dowadog.contents.ContentsObject.position
import com.takhyungmin.dowadog.urgent.UrgentAnimalData
import org.jetbrains.anko.ctx

class UrgentAnimalAdapter(val ctx : Context, val dataList: ArrayList<UrgentAnimalData>, val requestManager : RequestManager) : RecyclerView.Adapter<UrgentAnimalAdapter.Holder>() {

    //view 붙이기
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view : View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_urgent_animal_act_box, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

//        val requestOptions = RequestOptions().transforms(CenterCrop(), RoundedCorners(dpToPx(20)))
//
//        val drawable = ctx.getDrawable(R.drawable.abc) as GradientDrawable
//
//        holder.ani_img.setBackground(drawable)

        //뷰 바인딩!!
        holder.d_day.text = dataList[position].d_day
        requestManager.load(dataList[position].ani_img).into(holder.ani_img)
        //Log.v("image", dataList[position].ani_img)
        //requestManager.load(dataList[position].ani_gender).into(holder.ani_gender)
        //requestManager.load(dataList[position].ani_kind).into(holder.ani_kind)
        holder.ani_region.text = dataList[position].ani_region

        var heart_flag : Boolean = false

        holder.heart_touch.setOnClickListener {

            if(heart_flag == false)
            {
                holder.heart.isSelected = true
                heart_flag = true
            }
            else
            {
                holder.heart.isSelected = false
                heart_flag = false
            }
        }


        Glide.with(ctx).load(dataList[position].ani_img).into(holder.ani_img)
        holder.ani_img.setClipToOutline(true)
    }

    fun dpToPx(dp: Int): Int {
        val density = ctx.resources
                .displayMetrics
                .density
        return Math.round(dp.toFloat() * density)
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var heart_touch : RelativeLayout = itemView.findViewById(R.id.rl_heart_touch_rv_item_urgent_ani_act)
        var heart : ImageView = itemView.findViewById(R.id.img_heart_rv_item_urgent_ani_act)

        val d_day : TextView = itemView.findViewById(R.id.tv_day_rv_item_urgent_ani_act) as TextView
        val ani_img : ImageView = itemView.findViewById(R.id.img_item_urgent_ani_act) as ImageView
        //val ani_kind : ImageView = itemView.findViewById(R.id.img_kind_rv_item_urgent_ani_act) as ImageView
        //val ani_gender : ImageView = itemView.findViewById(R.id.img_gender_rv_item_urgent_ani_act) as ImageView
        val ani_region : TextView = itemView.findViewById(R.id.tv_region_rv_item_urgent_ani_act) as TextView

    }

}
