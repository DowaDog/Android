package com.takhyungmin.dowadog.adopt.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.jakewharton.rxbinding2.view.clicks
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.adopt.AdoptObject
import com.takhyungmin.dowadog.adopt.model.get.UrgentAnimalData

class AnimalFindNewAdapter(var newItems : ArrayList<UrgentAnimalData>, var newRequestManager : RequestManager) : RecyclerView.Adapter<AnimalFindNewViewHolder>() {
    var width = 0
    var height = 0
    var imgHeight = 0
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): AnimalFindNewViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_urgent_animal_act_box, parent, false)
        height = ((parent.measuredWidth / 2) * 1.16).toInt()
        //imgHeight = ((parent.measuredWidth / 2) * 0.825).toInt()

        return AnimalFindNewViewHolder(view)
    }

    override fun getItemCount(): Int = newItems.size

    override fun onBindViewHolder(holder: AnimalFindNewViewHolder, position: Int) {
        newRequestManager.load(newItems[position].ani_img).into(holder.ani_img)
        holder.ani_region.text = newItems[position].ani_region
        holder.tv_ani_kind.text = newItems[position].ani_kind
        holder.urgentLayout.visibility = View.GONE

        holder.ani_img.clicks().subscribe {
            AdoptObject.adoptAnimalFindFragmentPresnter.toApply()
        }

        holder.ani_img.setClipToOutline(true)

        val params = holder.newFrame.layoutParams
        params.height = height
        holder.newFrame.layoutParams = params

        holder.newFrame.clicks().subscribe {
            AdoptObject.adoptAnimalFindFragmentPresnter.toDtail(newItems[position].id)
        }

    }

    fun addAll(result : ArrayList<UrgentAnimalData>){
        newItems.addAll(result)
        notifyItemRangeInserted(newItems.size,
                result.size)
    }

}
