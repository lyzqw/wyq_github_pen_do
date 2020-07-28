package com.wyq_github_pen_do.activity

import android.content.Intent
import android.os.Bundle
import com.wyq.common.BaseActivity
import com.wyq_github_pen_do.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override val layoutId: Int = com.wyq_github_pen_do.R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 100)
    }
}