package com.takhyungmin.dowadog.login

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.takhyungmin.dowadog.BaseActivity
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.home.activity.HomeActivity
import com.takhyungmin.dowadog.signup.SignInfoWriteActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

class LoginActivity : BaseActivity(), View.OnClickListener {

    override fun onClick(v: View?) {
        when(v) {
            btn_look_login_act -> {
                startActivity<HomeActivity>()
            }
            btn_signup_login_act -> {
                startActivity<SignInfoWriteActivity>()
            }
            rl_login_act -> {
                downKeyboard(rl_login_act)
            }
            btn_login_act -> {
                startActivity<HomeActivity>()
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
    }

    private fun init() {
        btn_look_login_act.setOnClickListener(this)
        btn_signup_login_act.setOnClickListener(this)

        rl_login_act.setOnClickListener(this)
        btn_login_act.setOnClickListener(this)
    }

    private fun downKeyboard(view : View) {
        val imm: InputMethodManager = applicationContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


}
