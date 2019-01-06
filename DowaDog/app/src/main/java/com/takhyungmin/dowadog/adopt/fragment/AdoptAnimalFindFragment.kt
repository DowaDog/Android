package com.takhyungmin.dowadog.adopt.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.jakewharton.rxbinding2.view.clicks
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.adopt.AdoptObject
import com.takhyungmin.dowadog.adopt.activity.AdoptUrgentAnimalActivity
import com.takhyungmin.dowadog.adopt.adapter.AnimalFindNewAdapter
import com.takhyungmin.dowadog.adopt.adapter.AnimalFindUrgentAdapter
import com.takhyungmin.dowadog.adopt.model.get.UrgentAnimalData
import com.takhyungmin.dowadog.dogdetail.DogDetailActivity
import com.takhyungmin.dowadog.presenter.fragment.AdoptAnimalFindFragmentPresenter
import kotlinx.android.synthetic.main.fragment_find.*

class AdoptAnimalFindFragment : Fragment() {
    lateinit var adoptAnimalFindFragmentPresenter : AdoptAnimalFindFragmentPresenter
    lateinit var animalFindNewAdapter : AnimalFindNewAdapter
    lateinit var animlaFindUrgnetAdapter : AnimalFindUrgentAdapter
    lateinit var urgentRequestManager : RequestManager
    lateinit var newRequestManager : RequestManager

    var currentPage = 0
    var isLoading = false
    var isLast = false
    val totalPage = 3

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_find, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adoptAnimalFindFragmentPresenter = AdoptAnimalFindFragmentPresenter()
        adoptAnimalFindFragmentPresenter.view = this
        adoptAnimalFindFragmentPresenter.init()
        setOnBinding()

        AdoptObject.adoptAnimalFindFragmentPresnter = adoptAnimalFindFragmentPresenter
        adoptAnimalFindFragmentPresenter.requestNewList(currentPage, 10)
        adoptAnimalFindFragmentPresenter.requestUrgentList(currentPage, 2)

    }

    fun setOnBinding(){
        btn_find_fragment_more.clicks().subscribe {
            activity!!.startActivity(Intent(activity!!, AdoptUrgentAnimalActivity::class.java))
        }
    }

    fun toApply(){
        activity!!.startActivity(Intent(activity, DogDetailActivity::class.java))
    }

    fun initUrgent(urgentAnimalDatas : ArrayList<UrgentAnimalData>){
        urgentRequestManager = Glide.with(this)
        animlaFindUrgnetAdapter = AnimalFindUrgentAdapter(urgentAnimalDatas, urgentRequestManager, adoptAnimalFindFragmentPresenter)
        rv_find_fragment_urgent.layoutManager = GridLayoutManager(context, 2)
        rv_find_fragment_urgent.adapter = animlaFindUrgnetAdapter

    }

    fun initView(urgentAnimalDatas : ArrayList<UrgentAnimalData>){
        newRequestManager = Glide.with(this)

        animalFindNewAdapter = AnimalFindNewAdapter(urgentAnimalDatas, newRequestManager)

        rv_find_fragment_new.layoutManager = GridLayoutManager(context, 2)

        rv_find_fragment_new.adapter = animalFindNewAdapter

//        rv_find_fragment_new.scrollChangeEvents().subscribe {
//            if(!rv_urgent_ani_act.canScrollVertically(1)){
//                if (!isLoading and !isLast) {
//                    isLoading = true
//                    Log.v("scroll", currentPage.toString())
//                    currentPage++
//                    Handler().postDelayed(Runnable {
//                        //communityFragmentPresenter.nextPage(currentPage, itemCount)
//                        Log.v("scroll", "more")
//                        adoptAnimalFindFragmentPresenter.requestNewList(currentPage, 10)
//                    }, 2000)
//                }
//            }
//        }

        scroll_adopt_frame.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
            Log.v("scroll", "scroll")
            if (scrollY == ( v.getChildAt(0).height - v.height )) {
                //scroll in bottom
                Log.v("scroll", "bottom")
                if (!isLoading and !isLast) {
                    isLoading = true
                    Log.v("scroll", currentPage.toString())
                    currentPage++
                    Handler().postDelayed(Runnable {
                        //communityFragmentPresenter.nextPage(currentPage, itemCount)
                        Log.v("scroll", "more")
                        adoptAnimalFindFragmentPresenter.requestNewList(currentPage, 10)
                    }, 2000)
                }
            }

        })
    }

    fun loadNextPage(results : ArrayList<UrgentAnimalData>){
        Log.v("scroll", "add")
        animalFindNewAdapter.addAll(results)
        //currentPage += 1
        isLoading = false
        if (currentPage >= totalPage) {
            isLast = true
            //rv_urgent_ani_act.stopScroll()
        }
    }

    fun toDetail(id : Int){
        val intent = Intent(Intent(activity!!, DogDetailActivity::class.java))
        intent.putExtra("animalId", id)
        activity!!.startActivity(intent)
    }


}