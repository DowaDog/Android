package com.takhyungmin.dowadog.letter

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.takhyungmin.dowadog.BaseActivity
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.letter.model.LetterObject
import com.takhyungmin.dowadog.letter.model.get.GETLetterActivityResponse
import com.takhyungmin.dowadog.letter.model.get.GetReadLetterResponse
import com.takhyungmin.dowadog.presenter.activity.LetterActivityPresenter
import kotlinx.android.synthetic.main.activity_letter.*

class LetterActivity : BaseActivity() {

    private lateinit var letterActivityPresenter: LetterActivityPresenter

    lateinit var lettergetData: GETLetterActivityResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_letter)

        initPresenter()
        letterActivityPresenter.initView()
        letterActivityPresenter.requestData()
        letterActivityPresenter.requestReadLetterData()


        btn_back_letter_act.setOnClickListener{
            finish()
        }

    }


    fun responseData(data: GETLetterActivityResponse) {

        data?.let {

            var setletterAdapter = LetterAdapter(this, it.data)
            //여기에 받아온 데이터들을 가져와서 보여주는 것을 해야함 (함수로 만들던 여기에 구현하던)
           // Log.v("TAGG", data.toString())

            rv_letter_act.adapter = setletterAdapter
            rv_letter_act.layoutManager = LinearLayoutManager(this)

            rv_letter_act.setNestedScrollingEnabled(false)

        }
    }

    //view에 presenter 붙여주기
    private fun initPresenter() {

        letterActivityPresenter= LetterActivityPresenter()
        // 뷰 붙여주는 작업
        letterActivityPresenter.view = this
        LetterObject.letterActivityPresenter = letterActivityPresenter

        Log.v("TAGG", "letter 엑티비티 이닛프레젠터")

    }

    fun responseReadLetterData(data: GetReadLetterResponse) {

        Log.v("TAGG","이녀석은 여기로 들어온다")
        data?.let {
            it.message?.let {
                Log.v("TAGG",data.message)
            }

        }
    }


}
