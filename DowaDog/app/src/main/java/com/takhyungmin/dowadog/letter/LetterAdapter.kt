package com.takhyungmin.dowadog.letter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.letter.model.getletterData

class LetterAdapter(var ctx : Context, var dataList : ArrayList<getletterData>) : RecyclerView.Adapter<LetterAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
        val letter  : View = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_letter_act_card, parent, false)
        return Holder(letter)

    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.title.text = dataList[position].title
        holder.subtext.text = dataList[position].detail

        if(dataList[position].complete){
            holder.NewImg.visibility = View.GONE
            Log.v("asdf","사진 없음")
        }
        else{
            holder.NewImg.visibility = View.VISIBLE
        }

        Glide.with(ctx).load(dataList[position].imgPath).into(holder.BackgroundImag)

        holder.rootBtn.setClipToOutline(true)
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView!!) {
        var title : TextView = itemView!!.findViewById(R.id.tv_title_letter_act)
        var subtext : TextView = itemView!!.findViewById(R.id.tv_talk_letter_act)
        var NewImg : ImageView = itemView!!.findViewById(R.id.rv_new_img_letter_act)
        var BackgroundImag : ImageView = itemView!!.findViewById(R.id.rv_ll_background_img_letter_act)
        var rootBtn : RelativeLayout = itemView!!.findViewById(R.id.rl_root_letter_act)

    }
}
