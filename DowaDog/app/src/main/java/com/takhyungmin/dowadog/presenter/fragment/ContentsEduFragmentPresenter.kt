package com.takhyungmin.dowadog.presenter.fragment

import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.contents.adapter.ContentsEduItem
import com.takhyungmin.dowadog.contents.fragment.ContentsEduFragment
import com.takhyungmin.dowadog.presenter.BasePresenter

class ContentsEduFragmentPresenter : BasePresenter<ContentsEduFragment>() {

    lateinit var contentsEduItems : ArrayList<ContentsEduItem>
    fun initView(){
        contentsEduItems = ArrayList()


        //이 아래는 서버 통신 해야 하는 부분.
        contentsEduItems.add(ContentsEduItem("제모오오오오옹오옥1", "서브브으으으으1", R.drawable.pic1, false))
        contentsEduItems.add(ContentsEduItem("제모오오오오옹오옥2", "서브브으으으으2", R.drawable.pic1, true))
        contentsEduItems.add(ContentsEduItem("제모오오오오옹오옥3", "서브브으으으으3", R.drawable.pic1, true))
        contentsEduItems.add(ContentsEduItem("제모오오오오옹오옥4", "서브브으으으으4", R.drawable.pic1, false))
        contentsEduItems.add(ContentsEduItem("제모오오오오옹오옥5", "서브브으으으으5", R.drawable.pic1, true))
        contentsEduItems.add(ContentsEduItem("제모오오오오옹오옥6", "서브브으으으으6", R.drawable.pic1, false))
        contentsEduItems.add(ContentsEduItem("제모오오오오옹오옥7", "서브브으으으으7", R.drawable.pic1, false))



        view!!.initView(contentsEduItems)
    }

    fun toDetail(){
        view!!.toDetail()
    }
}