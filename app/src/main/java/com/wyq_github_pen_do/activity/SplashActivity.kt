package com.wyq_github_pen_do.activity

import android.content.Intent
import android.os.Bundle
import com.wyq.common.base.BaseActivity
import com.wyq_github_pen_do.R
import com.wyq_github_pen_do.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override val layoutId: Int = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 300)
    }

    override fun initView() {

    }

    override fun loadData() {
    }

    override fun initListener() {
    }
}