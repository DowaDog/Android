package com.takhyungmin.dowadog.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.takhyungmin.dowadog.BaseActivity
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.home.activity.HomeActivity
import com.takhyungmin.dowadog.login.model.LoginObject
import com.takhyungmin.dowadog.login.model.get.GetLoginData
import com.takhyungmin.dowadog.login.model.post.PostRefreshData
import com.takhyungmin.dowadog.presenter.activity.LoginActivityPresenter
import com.takhyungmin.dowadog.signup.activity.SignInfoWriteActivity
import com.takhyungmin.dowadog.utils.ApplicationData
import com.takhyungmin.dowadog.utils.SharedPreferenceController
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

class LoginActivity : BaseActivity(), View.OnClickListener {

    override fun onClick(v: View?) {
        when(v) {
            btn_look_login_act -> {
                ApplicationData.loginState = false
                startActivity<HomeActivity>()
            }
            btn_signup_login_act -> {
                startActivity<SignInfoWriteActivity>()
            }
            rl_login_act -> {
                downKeyboard(rl_login_act)
            }
            btn_login_act -> {
                loginActivityPresenter.requestLogin(et_id_login_act.text.toString(),
                        et_pw_login_act.text.toString())

            }

        }
    }

    lateinit var loginActivityPresenter: LoginActivityPresenter
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
        loginActivityPresenter = LoginActivityPresenter()
        loginActivityPresenter.view = this
        LoginObject.loginActivityPresenter = loginActivityPresenter
        Log.v("access", SharedPreferenceController.getAccessToken(this))
        loginActivityPresenter.requestRefresh(SharedPreferenceController.getRefreshToken(this))
    }

    private fun downKeyboard(view : View) {
        val imm: InputMethodManager = applicationContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun responseLogin(data : GetLoginData){
        SharedPreferenceController.setAccessToken(this, data.accessToken.data)
        SharedPreferenceController.setRefreshToken(this, data.refreshToken.data)
        SharedPreferenceController.setRefreshTokenExpired(this, data.refreshToken.expiredAt)
        SharedPreferenceController.setAccessTokenExpired(this, data.accessToken.expiredAt)
        SharedPreferenceController.setId(this, et_id_login_act.text.toString())
        SharedPreferenceController.setPwd(this, et_pw_login_act.text.toString())

        ApplicationData.loginState = true
        startActivity<HomeActivity>()
    }

    fun responseRefresh(data : PostRefreshData){
        SharedPreferenceController.setAccessToken(this, data.data)
        SharedPreferenceController.setAccessTokenExpired(this, data.expiredAt)
        Log.v("access", SharedPreferenceController.getAccessToken(this))

    }


}
