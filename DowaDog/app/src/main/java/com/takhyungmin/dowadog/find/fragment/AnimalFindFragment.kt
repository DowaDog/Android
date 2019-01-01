package com.takhyungmin.dowadog.find.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.jakewharton.rxbinding2.view.clicks
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.dogdetail.DogDetailActivity
import com.takhyungmin.dowadog.filter.FilterActivity
import com.takhyungmin.dowadog.find.adapter.AnimalFindNewAdapter
import com.takhyungmin.dowadog.find.adapter.AnimalFindUrgentAdapter
import com.takhyungmin.dowadog.presenter.fragment.AnimalFindFragmentPresenter
import com.takhyungmin.dowadog.urgent.Activity.UrgentAnimalActivity
import com.takhyungmin.dowadog.urgent.UrgentAnimalData
import kotlinx.android.synthetic.main.fragment_find.*

class AnimalFindFragment : Fragment() {
    lateinit var animalFindFragmentPresenter : AnimalFindFragmentPresenter
    lateinit var animalFindNewAdapter : AnimalFindNewAdapter
    lateinit var animlaFindUrgnetAdapter : AnimalFindUrgentAdapter
    lateinit var urgentRequestManager : RequestManager
    lateinit var newRequestManager : RequestManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_find, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animalFindFragmentPresenter = AnimalFindFragmentPresenter()
        animalFindFragmentPresenter.view = this
        animalFindFragmentPresenter.init()
        setOnBinding()
    }

    fun init(urgentItems : ArrayList<UrgentAnimalData>, newItems : ArrayList<UrgentAnimalData>){
        urgentRequestManager = Glide.with(this)
        newRequestManager = Glide.with(this)

        animalFindNewAdapter = AnimalFindNewAdapter(newItems, newRequestManager, animalFindFragmentPresenter)
        animlaFindUrgnetAdapter = AnimalFindUrgentAdapter(urgentItems, urgentRequestManager, animalFindFragmentPresenter)


        rv_find_fragment_new.layoutManager = GridLayoutManager(context, 2)
        rv_find_fragment_urgent.layoutManager = GridLayoutManager(context, 2)

        rv_find_fragment_new.adapter = animalFindNewAdapter
        rv_find_fragment_urgent.adapter = animlaFindUrgnetAdapter
    }

    fun toApply(){
        startActivity(Intent(activity, DogDetailActivity::class.java))
    }

    fun setOnBinding(){
        btn_find_fragment_filter.clicks().subscribe {
            startActivity(Intent(activity, FilterActivity::class.java))
        }

        btn_find_fragment_more.clicks().subscribe {
            startActivity(Intent(activity, UrgentAnimalActivity::class.java))
        }
    }
}