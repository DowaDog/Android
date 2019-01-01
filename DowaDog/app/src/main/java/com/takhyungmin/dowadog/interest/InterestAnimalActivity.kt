package com.takhyungmin.dowadog.interest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.bumptech.glide.RequestManager
import com.takhyungmin.dowadog.BaseActivity
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.interest.adapter.InterestAnimalAdapter
import com.takhyungmin.dowadog.urgent.UrgentAnimalData
import kotlinx.android.synthetic.main.activity_interest_animal.*
import kotlinx.android.synthetic.main.activity_search_result.*

class InterestAnimalActivity : BaseActivity(), View.OnClickListener {

    override fun onClick(v: View?) {
        when (v) {
            btn_back_interest_animal_act-> {
                //back버튼 누르면
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interest_animal)

        init()
        setRVAdapter()
    }

    private fun init(){
        btn_back_interest_animal_act.setOnClickListener(this)
    }

    private fun setRVAdapter(){

        var animalItem: ArrayList<UrgentAnimalData> = ArrayList()

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

        //animalItem.add(UrgentAnimalData("D-3","", "","","[인천] 러시안 블루" ))

        var interestAnimalAdapter = InterestAnimalAdapter(this, animalItem)

        rv_interest_ani_act.adapter = interestAnimalAdapter
        rv_interest_ani_act.layoutManager = GridLayoutManager(this, 2)
    }
}
