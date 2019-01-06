package com.takhyungmin.dowadog.letter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.takhyungmin.dowadog.R
import kotlinx.android.synthetic.main.activity_letter.*

class LetterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_letter)

        setletterAdapter()

        btn_back_letter_act.setOnClickListener{
            finish()
        }

    }

    private fun setletterAdapter() {

        var letteritem : ArrayList<letterData> = ArrayList()

        letteritem.add(letterData("http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG"))
        letteritem.add(letterData("http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG"))
        letteritem.add(letterData("http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG"))
        letteritem.add(letterData("http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG"))
        letteritem.add(letterData("http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG"))
        letteritem.add(letterData("http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG"))
        letteritem.add(letterData("http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG"))
        letteritem.add(letterData("http://img.hani.co.kr/imgdb/resize/2018/0907/00502739_20180907.JPG"))


        var setletterAdapter = LetterAdapter(this, letteritem)

        rv_letter_act.adapter = setletterAdapter
        rv_letter_act.layoutManager = LinearLayoutManager(this)

        rv_letter_act.setNestedScrollingEnabled(false)
    }

}
