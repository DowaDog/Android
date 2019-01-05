package com.takhyungmin.dowadog.searchresult.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.dogdetail.DogDetailActivity
import com.takhyungmin.dowadog.searchresult.model.ggg.Content
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*


class SearchResultAdapter(val ctx: Context, val dataList: ArrayList<Content>, val requestManager: RequestManager) : RecyclerView.Adapter<SearchResultAdapter.Holder>() {
    override fun onBindViewHolder(holder: SearchResultAdapter.Holder, position: Int) {

//        val requestOptions = RequestOptions().transforms(CenterCrop(), RoundedCorners(dpToPx(20)))
//
//        val drawable = ctx.getDrawable(R.drawable.abc) as GradientDrawable
//
//        holder.ani_img.setBackground(drawable)

//        if (position == 0 || position == 1) {
//            //마진 동적 설정
//            val dp = ctx.resources.displayMetrics.density
//            val layoutParams = holder.newFrame.getLayoutParams() as GridLayoutManager.LayoutParams
//            layoutParams.topMargin = (40 * dp).toInt()
//            holder.newFrame.layoutParams = layoutParams
//        }


        //뷰 바인딩!!
        val now = System.currentTimeMillis()
        val date = Date(now)
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
        val beginDate = sdf.format(date)
        val today = sdf.parse(beginDate)

        val endDate = sdf.parse(dataList[position].noticeEddt)
        val diff = endDate.time - today.time
        val dDay = diff / (24 * 60 * 60 * 1000)


        holder.d_day.text = "D-" + dDay.toString()
        requestManager.load(dataList[position].thumbnailImg).into(holder.ani_img)
        //Log.v("image", dataList[position].ani_img)
        //requestManager.load(dataList[position].ani_gender).into(holder.ani_gender)
        //requestManager.load(dataList[position].ani_kind).into(holder.ani_kind)
        holder.ani_region.text = "[" + dataList[position].region + "] "

        var heart_flag: Boolean = false


        Glide.with(ctx).load(dataList[position].thumbnailImg).into(holder.ani_img)
        holder.ani_img.setClipToOutline(true)
        holder.tv_ani_kind.text = dataList[position].kindCd

        val params = holder.newFrame.layoutParams
        params.height = height
        holder.newFrame.layoutParams = params

        if(dataList[position].sexCd == "F"){
            holder.iv_sex.setImageResource(R.drawable.woman_icon_1227)
        }else{
            holder.iv_sex.setImageResource(R.drawable.man_icon_1227)
        }

        if(dataList[position].type == "개"){
            holder.iv_dog_or_cat.setImageResource(R.drawable.dog_icon_1227)
        }else {
            holder.iv_dog_or_cat.setImageResource(R.drawable.cat_icon_1227)
        }

        holder.newFrame.setOnClickListener {
            ctx.startActivity<DogDetailActivity>("animalId" to dataList[position].id)
        }

    }

    var height = 0

    //view 붙이기
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SearchResultAdapter.Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_urgent_animal_act_box, p0, false)
        height = ((p0.measuredWidth / 2) * 1.16).toInt()
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    fun dpToPx(dp: Int): Int {
        val density = ctx.resources
                .displayMetrics
                .density
        return Math.round(dp.toFloat() * density)
    }

    fun addAll(result: ArrayList<Content>) {
        dataList.addAll(result)
        notifyItemRangeInserted(dataList.size,
                result.size)
//        Log.v("position1", dataList.size.toString())
//        Log.v("position2", result.size.toString())

    }

    fun getDDay(year: Int, month: Int, day: Int) : Int{
        var today : Calendar = Calendar.getInstance()
        var dDay : Calendar = Calendar.getInstance()
        dDay.set(year, month, day)

        var lDDay = dDay.timeInMillis / (24 * 60 * 60 * 1000)
        var lToday = today.timeInMillis / (24 * 60 * 60 * 1000)

        var substract : Int = (lDDay - lToday).toInt()
        return substract
    }


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var newFrame: LinearLayout = itemView.findViewById(R.id.rl_root_view_rv_item_urgent_anmal_act_box)
        // var heart_touch: RelativeLayout = itemView.findViewById(R.id.rl_heart_touch_rv_item_urgent_ani_act)
        // var heart: ImageView = itemView.findViewById(R.id.img_heart_rv_item_urgent_ani_act)
        val d_day: TextView = itemView.findViewById(R.id.tv_day_rv_item_urgent_ani_act) as TextView
        val ani_img: ImageView = itemView.findViewById(R.id.img_item_urgent_ani_act) as ImageView
        val tv_ani_kind: TextView = itemView.findViewById(R.id.tv_kind_dog_rv_item_urgent_ani_act) as TextView
        //val ani_gender : ImageView = itemView.findViewById(R.id.img_gender_rv_item_urgent_ani_act) as ImageView
        val ani_region: TextView = itemView.findViewById(R.id.tv_region_rv_item_urgent_ani_act) as TextView

        val iv_dog_or_cat: ImageView = itemView.findViewById(R.id.img_kind_rv_item_urgent_ani_act) as ImageView
        val iv_sex: ImageView = itemView.findViewById(R.id.img_gender_rv_item_urgent_ani_act) as ImageView
    }

}

//
//(val ctx: Context, val dataList: ArrayList<UrgentAnimalData>) : RecyclerView.Adapter<SearchResultAdapter.Holder>() {
//
//    //view 붙이기
//    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
//        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_urgent_animal_act_box, p0, false)
//        return Holder(view)
//    }
//
//    override fun getItemCount(): Int = dataList.size
//
//    override fun onBindViewHolder(holder: Holder, position: Int) {
//
////        val requestOptions = RequestOptions().transforms(CenterCrop(), RoundedCorners(dpToPx(20)))
////
////        val drawable = ctx.getDrawable(R.drawable.abc) as GradientDrawable
////
////        holder.ani_img.setBackground(drawable)
//
//
//        if (position == 0 || position == 1) {
//            //마진 동적 설정
//            val dp = ctx.resources.displayMetrics.density
//            val layoutParams = holder.rootView.getLayoutParams() as GridLayoutManager.LayoutParams
////            val rootLayoutParams : RelativeLayout.LayoutParams = holder.rootView.layoutParams  as RelativeLayout.LayoutParams
//            layoutParams.topMargin = (40 * dp).toInt()
//            holder.rootView.layoutParams = layoutParams
//        }
//
//        //뷰 바인딩!!
//        holder.d_day.text = dataList[position].d_day
//        //Log.v("image", dataList[position].ani_img)
//        //requestManager.load(dataList[position].ani_gender).into(holder.ani_gender)
//        //requestManager.load(dataList[position].ani_kind).into(holder.ani_kind)
//        holder.ani_region.text = dataList[position].ani_region
//
//        var heart_flag: Boolean = false
//
//        holder.heart_touch.setOnClickListener {
//
//            if (heart_flag == false) {
//                holder.heart.isSelected = true
//                heart_flag = true
//            } else {
//                holder.heart.isSelected = false
//                heart_flag = false
//            }
//        }
//
//
//        Glide.with(ctx).load(dataList[position].ani_img).into(holder.ani_img)
//        holder.ani_img.setClipToOutline(true)
//        holder.tv_ani_kind.text = dataList[position].ani_kind
//    }
//
//    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//        var rootView: LinearLayout = itemView.findViewById(R.id.rl_root_view_rv_item_urgent_anmal_act_box) as LinearLayout
//        var heart_touch: RelativeLayout = itemView.findViewById(R.id.rl_heart_touch_rv_item_urgent_ani_act)
//        var heart: ImageView = itemView.findViewById(R.id.img_heart_rv_item_urgent_ani_act)
//        val tv_ani_kind: TextView = itemView.findViewById(R.id.tv_kind_dog_rv_item_urgent_ani_act) as TextView
//        val d_day: TextView = itemView.findViewById(R.id.tv_day_rv_item_urgent_ani_act) as TextView
//        val ani_img: ImageView = itemView.findViewById(R.id.img_item_urgent_ani_act) as ImageView
//        //val ani_gender : ImageView = itemView.findViewById(R.id.img_gender_rv_item_urgent_ani_act) as ImageView
//        val ani_region: TextView = itemView.findViewById(R.id.tv_region_rv_item_urgent_ani_act) as TextView
//
//    }
//