package com.takhyungmin.dowadog.adopt.activity

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.view.scrollChangeEvents
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.adopt.AdoptObject
import com.takhyungmin.dowadog.adopt.adapter.UrgentAnimalAdapter
import com.takhyungmin.dowadog.adopt.model.get.UrgentAnimalData
import com.takhyungmin.dowadog.presenter.activity.AdoptUrgentAnimalActivityPresenter
import kotlinx.android.synthetic.main.activity_urgent_animal.*

class AdoptUrgentAnimalActivity : AppCompatActivity() {

    lateinit var urgentAnimalAdapter: UrgentAnimalAdapter
    lateinit var requestManager: RequestManager
    lateinit var adoptUrgentAnimalActivityPresenter : AdoptUrgentAnimalActivityPresenter

    var currentPage = 0
    var isLoading = false
    var isLast = false
    val totalPage = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_urgent_animal)
        init()

        setOnBinding()
    }

    fun init(){
        adoptUrgentAnimalActivityPresenter = AdoptUrgentAnimalActivityPresenter()
        adoptUrgentAnimalActivityPresenter.view = this
        AdoptObject.adoptUrgentAnimalActivityPresenter = adoptUrgentAnimalActivityPresenter
        adoptUrgentAnimalActivityPresenter.requestUrgentList(currentPage, 10)
    }

    fun initView(urgentAnimalDatas : ArrayList<UrgentAnimalData>){
        requestManager = Glide.with(this)
        urgentAnimalAdapter = UrgentAnimalAdapter(this, urgentAnimalDatas, requestManager)
        //rv_urgent_ani_act.setFocusable(false)
        rv_urgent_ani_act.adapter = urgentAnimalAdapter
        rv_urgent_ani_act.layoutManager = GridLayoutManager(this, 2)

        rv_urgent_ani_act.scrollChangeEvents().subscribe {
            if(!rv_urgent_ani_act.canScrollVertically(1)){
                if (!isLoading and !isLast) {
                    isLoading = true
                    Log.v("scroll", currentPage.toString())
                    currentPage++
                    Handler().postDelayed(Runnable {
                        //communityFragmentPresenter.nextPage(currentPage, itemCount)
                        Log.v("scroll", "more")
                        adoptUrgentAnimalActivityPresenter.requestUrgentList(currentPage, 10)
                    }, 2000)
                }
            }
        }

//        rv_urgent_ani_act.addOnScrollListener(object : RecyclerView.OnScrollListener(){
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                if(!rv_urgent_ani_act.canScrollVertically(1)){
//                    if (!isLoading and !isLast) {
//                        isLoading = true
//                        Log.v("scroll", currentPage.toString())
//                        currentPage++
//                        Handler().postDelayed(Runnable {
//                            //communityFragmentPresenter.nextPage(currentPage, itemCount)
//                            Log.v("scroll", "more")
//                            adoptUrgentAnimalActivityPresenter.requestUrgentList(currentPage, 10)
//                        }, 2000)
//                    }
//                }
//            }
//        })

    }
    fun loadNextPage(results : ArrayList<UrgentAnimalData>){
        Log.v("scroll", "add")
        urgentAnimalAdapter.addAll(results)
        //currentPage += 1
        isLoading = false
        if (currentPage >= totalPage) {
            isLast = true
            //rv_urgent_ani_act.stopScroll()
        }
    }

    fun setOnBinding(){
        btn_urgent_back.clicks().subscribe {
            finish()
        }
    }
}
