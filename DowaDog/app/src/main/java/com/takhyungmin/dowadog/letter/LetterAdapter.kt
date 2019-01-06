package com.takhyungmin.dowadog.letter

import android.content.Context
import android.media.Image
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.takhyungmin.dowadog.R

class LetterAdapter(var ctx : Context, var dataList : ArrayList<letterData>) : RecyclerView.Adapter<LetterAdapter.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
        val letter  : View = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_letter_act_card, parent, false)
        return Holder(letter)

    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        //##서버 통신할 때, new이미지 여부
//        if(dataList[position].newimg == null){
//            holder.NewImg.visibility = View.GONE
//            Log.v("asdf","사진 없음")
//        }
//        else{
//            holder.NewImg.visibility = View.VISIBLE
//        }

        Glide.with(ctx).load(dataList[position].backgroundimg).into(holder.BackgroundImag)
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView!!) {
        //var NewImg : ImageView = itemView!!.findViewById(R.id.rv_new_img_letter_act)
        var BackgroundImag : ImageView = itemView!!.findViewById(R.id.rv_ll_background_img_letter_act)
    }
}

data class letterData(
        //var newimg : String,
        var backgroundimg : String
)

