package com.takhyungmin.dowadog.presenter.activity

import android.util.Log
import com.takhyungmin.dowadog.letter.LetterActivity
import com.takhyungmin.dowadog.letter.model.get.GETLetterActivityResponse
import com.takhyungmin.dowadog.letter.model.get.LetterModel
import com.takhyungmin.dowadog.presenter.BasePresenter

class LetterActivityPresenter : BasePresenter<LetterActivity>() {

    private val letterModel : LetterModel by lazy {
        LetterModel()
    }

    fun initView() {

    }

    //모델에게 일을 시킴
    fun requestData(){
        Log.v("TAGG", "프레젠터 리퀘스트데이터")
        letterModel.getMypageData()
    }
    //view에게 데이터 전달
    fun responseData(data : GETLetterActivityResponse){
        Log.v("TAGG", "프레젠터 리스폰스데이터")
        view!!.responseData(data)
    }
}