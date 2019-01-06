package com.takhyungmin.dowadog.adopt.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.jakewharton.rxbinding2.view.clicks
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.adopt.AdoptObject
import com.takhyungmin.dowadog.adopt.model.get.UrgentAnimalData

class UrgentAnimalAdapter(val ctx : Context, val dataList: ArrayList<UrgentAnimalData>, val requestManager : RequestManager) : RecyclerView.Adapter<UrgentAnimalAdapter.Holder>() {
    var height = 0

    //view 붙이기
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view : View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_urgent_animal_act_box, p0, false)
        height = ((p0.measuredWidth / 2) * 1.16).toInt()
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
        holder.tv_ani_kind.text = dataList[position].ani_kind

        val params = holder.newFrame.layoutParams
        params.height = height
        holder.newFrame.layoutParams = params

        holder.newFrame.clicks().subscribe {
            AdoptObject.adoptUrgentAnimalActivityPresenter.toDetailActivity(dataList[position].id)
        }
    }

    fun dpToPx(dp: Int): Int {
        val density = ctx.resources
                .displayMetrics
                .density
        return Math.round(dp.toFloat() * density)
    }

    fun addAll(result : ArrayList<UrgentAnimalData>){
        dataList.addAll(result)
        notifyItemRangeInserted(dataList.size,
                result.size)
//        Log.v("position1", dataList.size.toString())
//        Log.v("position2", result.size.toString())

    }


    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var newFrame : LinearLayout = itemView.findViewById(R.id.rl_root_view_rv_item_urgent_anmal_act_box)

        var heart_touch : RelativeLayout = itemView.findViewById(R.id.rl_heart_touch_rv_item_urgent_ani_act)
        var heart : ImageView = itemView.findViewById(R.id.img_heart_rv_item_urgent_ani_act)

        val d_day : TextView = itemView.findViewById(R.id.tv_day_rv_item_urgent_ani_act) as TextView
        val ani_img : ImageView = itemView.findViewById(R.id.img_item_urgent_ani_act) as ImageView
        val tv_ani_kind : TextView = itemView.findViewById(R.id.tv_kind_dog_rv_item_urgent_ani_act) as TextView
        //val ani_gender : ImageView = itemView.findViewById(R.id.img_gender_rv_item_urgent_ani_act) as ImageView
        val ani_region : TextView = itemView.findViewById(R.id.tv_region_rv_item_urgent_ani_act) as TextView

    }

}
