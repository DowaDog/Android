package com.takhyungmin.dowadog.donation

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.takhyungmin.dowadog.R

class DoantionRecyclerViewAdpater(var ctx: Context, var dataList: ArrayList<DoantionRecyclerViewAdpaterData>) : RecyclerView.Adapter<DoantionRecyclerViewAdpater.DonationRecyclerViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonationRecyclerViewHolder {

        var view = LayoutInflater.from(ctx).inflate(R.layout.rv_item_donation_act, parent, false)

        return DonationRecyclerViewHolder(view)
    }


    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: DonationRecyclerViewHolder, position: Int) {

        // 이미지
        Glide.with(ctx).load(dataList[position].img).into(holder.image)

        holder.image.setClipToOutline(true)
        // 이름
        holder.name.text = dataList[position].name
        // 설명
        holder.explain.text = dataList[position].explain
        // 링크
        holder.link.text = dataList[position].link
    }

    inner class DonationRecyclerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var image : ImageView = itemView.findViewById(R.id.iv_item_donation_act)
        var name : TextView = itemView.findViewById(R.id.tv_store_item_donation_act)
        var explain : TextView = itemView.findViewById(R.id.tv_store_explain_item_donation_act)
        var link : TextView = itemView.findViewById(R.id.tv_link_item_donation_act)

    }
}

data class DoantionRecyclerViewAdpaterData(
        var img: String,
        var name: String,
        var explain: String,
        var link: String
)