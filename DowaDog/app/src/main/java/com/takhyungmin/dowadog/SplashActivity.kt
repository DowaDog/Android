package com.takhyungmin.dowadog

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.takhyungmin.dowadog.login.LoginActivity
import com.takhyungmin.dowadog.utils.ApplicationData
import com.takhyungmin.dowadog.utils.GifDrawableImageViewTarget
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        ApplicationData.applicationContext = applicationContext

        Glide.with(this).load(R.drawable.splash).into(GifDrawableImageViewTarget(splash_image,1))

        val handler = Handler()
        handler.postDelayed(Runnable {
            startActivity<LoginActivity>()
            finish()
        }, 3000)


    }

}