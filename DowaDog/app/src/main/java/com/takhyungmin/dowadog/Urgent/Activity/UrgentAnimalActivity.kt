package com.takhyungmin.dowadog.urgent.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.jakewharton.rxbinding2.view.clicks
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.urgent.Adapter.UrgentAnimalAdapter
import com.takhyungmin.dowadog.urgent.UrgentAnimalData
import kotlinx.android.synthetic.main.activity_urgent_animal.*

class UrgentAnimalActivity : AppCompatActivity() {

    lateinit var urgentAnimalAdapter: UrgentAnimalAdapter
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_urgent_animal)

        //RecyclerView
        recycler_animal()
        setOnBinding()
    }

    fun recycler_animal() {

        var animalItem: ArrayList<UrgentAnimalData> = ArrayList()

        requestManager = Glide.with(this)

        animalItem.add(UrgentAnimalData("D-1","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","믹스견", "[충청]"))
        animalItem.add(UrgentAnimalData("D-2","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","페르시안", "[전라도] "))
        animalItem.add(UrgentAnimalData("D-1","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","믹스견", "[충청]"))
        animalItem.add(UrgentAnimalData("D-2","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","페르시안", "[전라도] "))
        animalItem.add(UrgentAnimalData("D-1","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","믹스견", "[충청]"))
        animalItem.add(UrgentAnimalData("D-2","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","페르시안", "[전라도] "))
        animalItem.add(UrgentAnimalData("D-1","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","믹스견", "[충청]"))
        animalItem.add(UrgentAnimalData("D-2","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","페르시안", "[전라도] "))
        animalItem.add(UrgentAnimalData("D-1","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","믹스견", "[충청]"))
        animalItem.add(UrgentAnimalData("D-2","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","페르시안", "[전라도] "))
        animalItem.add(UrgentAnimalData("D-1","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","믹스견", "[충청]"))
        animalItem.add(UrgentAnimalData("D-2","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","페르시안", "[전라도] "))

        //animalItem.add(UrgentAnimalData("D-3","", "","","[인천] 러시안 블루" ))

        urgentAnimalAdapter = UrgentAnimalAdapter(this, animalItem, requestManager)

        rv_urgent_ani_act.adapter = urgentAnimalAdapter
        rv_urgent_ani_act.layoutManager = GridLayoutManager(this, 2)
    }

    fun setOnBinding(){
        btn_urgent_back.clicks().subscribe {
            finish()
        }
    }
}
