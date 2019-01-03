package com.takhyungmin.dowadog.find.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.jakewharton.rxbinding2.view.clicks
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.presenter.fragment.AnimalFindFragmentPresenter
import com.takhyungmin.dowadog.urgent.UrgentAnimalData

class AnimalFindUrgentAdapter(var urgentItems : ArrayList<UrgentAnimalData>,
                              var urgentRequestManager : RequestManager, var animalFindFragmentPresenter: AnimalFindFragmentPresenter) : RecyclerView.Adapter<AnimalFindUrgentViewHolder>() {

    var width = 0
    var height = 0

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): AnimalFindUrgentViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_urgent_animal_act_box, parent, false)
        height = ((parent.measuredWidth / 2) * 1.16).toInt()

        return AnimalFindUrgentViewHolder(view)
    }

    override fun getItemCount(): Int = urgentItems.size

    override fun onBindViewHolder(holder: AnimalFindUrgentViewHolder, position: Int) {
        urgentRequestManager.load(urgentItems[position].ani_img).into(holder.ani_img)
        holder.ani_region.text = urgentItems[position].ani_region
        holder.tv_ani_kind.text = urgentItems[position].ani_kind

        holder.ani_img.clicks().subscribe {
            animalFindFragmentPresenter.toApply()
        }

        holder.ani_img.setClipToOutline(true)

        val params = holder.newFrame.layoutParams
        params.height = height
        holder.newFrame.layoutParams = params

    }
}