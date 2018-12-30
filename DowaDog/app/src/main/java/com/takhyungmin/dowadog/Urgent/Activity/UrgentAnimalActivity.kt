package com.takhyungmin.dowadog.urgent.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.contents.ContentsObject.position
import com.takhyungmin.dowadog.urgent.Adapter.UrgentAnimalAdapter
import com.takhyungmin.dowadog.urgent.UrgentAnimalData
import kotlinx.android.synthetic.main.activity_urgent_animal.*
import kotlinx.android.synthetic.main.rv_item_urgent_animal_act_box.*
import org.jetbrains.anko.ctx

class UrgentAnimalActivity : AppCompatActivity() {

    lateinit var urgentAnimalAdapter: UrgentAnimalAdapter
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_urgent_animal)

        //RecyclerView
        recycler_animal()

    }

    fun recycler_animal() {

        var animalItem: ArrayList<UrgentAnimalData> = ArrayList()

        requestManager = Glide.with(this)

        animalItem.add(UrgentAnimalData("D-1","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","[충청] 믹스견"))
        animalItem.add(UrgentAnimalData("D-2","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","[전라도] 페르시안"))
        //animalItem.add(UrgentAnimalData("D-3","", "","","[인천] 러시안 블루" ))

        urgentAnimalAdapter = UrgentAnimalAdapter(this, animalItem, requestManager)

        rv_urgent_ani_act.adapter = urgentAnimalAdapter
        rv_urgent_ani_act.layoutManager = GridLayoutManager(this, 2)
    }
}
