package com.takhyungmin.dowadog.presenter.fragment

import com.takhyungmin.dowadog.find.fragment.AnimalFindFragment
import com.takhyungmin.dowadog.presenter.BasePresenter
import com.takhyungmin.dowadog.urgent.UrgentAnimalData

class AnimalFindFragmentPresenter : BasePresenter<AnimalFindFragment>() {

    lateinit var urgentItems : ArrayList<UrgentAnimalData>
    lateinit var newItems : ArrayList<UrgentAnimalData>

    fun init(){
        urgentItems = ArrayList()
        newItems = ArrayList()
        urgentItems.add(UrgentAnimalData("D-1","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","믹스견", "[충청]"))
        urgentItems.add(UrgentAnimalData("D-2","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","페르시안", "[전라도] "))
        newItems.add(UrgentAnimalData("D-1","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","믹스견", "[충청]"))
        newItems.add(UrgentAnimalData("D-2","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","페르시안", "[전라도] "))
        newItems.add(UrgentAnimalData("D-1","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","믹스견", "[충청]"))
        newItems.add(UrgentAnimalData("D-2","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","페르시안", "[전라도] "))
        newItems.add(UrgentAnimalData("D-1","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","믹스견", "[충청]"))
        newItems.add(UrgentAnimalData("D-2","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","페르시안", "[전라도] "))
        newItems.add(UrgentAnimalData("D-1","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","믹스견", "[충청]"))
        newItems.add(UrgentAnimalData("D-2","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","페르시안", "[전라도] "))
        newItems.add(UrgentAnimalData("D-1","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","믹스견", "[충청]"))
        newItems.add(UrgentAnimalData("D-2","http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG","페르시안", "[전라도] "))

        view!!.init(urgentItems, newItems)
    }
}