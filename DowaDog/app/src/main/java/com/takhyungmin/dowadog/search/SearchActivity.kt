package com.takhyungmin.dowadog.search

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.takhyungmin.dowadog.R
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.sdk25.coroutines.textChangedListener

class SearchActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v!!) {

            // 백버튼
            btn_back_search_act -> finish()

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        setSearchBtnTextChangeListener()
    }

    fun setSearchBtnTextChangeListener(){
        et_keyword_search_act.textChangedListener {
            afterTextChanged {
                if (et_keyword_search_act.text.toString().length > 0) {
                    btn_search_search_act.setBackgroundColor(Color.parseColor("#40D39F"))
                } else {
                    btn_search_search_act.setBackgroundColor(Color.parseColor("#C5C5C5"))
                }
            }
        }
    }

}

