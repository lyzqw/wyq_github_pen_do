package com.wyq_github_pen_do.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.wyq.common.base.BaseActivity
import com.wyq.common.permission.PermissionHelper
import com.wyq_github_pen_do.R
import com.wyq_github_pen_do.databinding.ActivitySplashBinding
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override val layoutId: Int = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.postDelayed({
            if (ActivityCompat.checkSelfPermission(
                    this@SplashActivity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                startMain()
                return@postDelayed
            }


            lifecycleScope.launch {
                val result = PermissionHelper.requestPermissions(
                    this@SplashActivity,
                    listOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                )
                if (result.isGranted()) {
                    startMain()
                }
            }
        }, 300)
    }

    private fun startMain() {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        finish()
    }

    override fun initView() {

    }

    override fun loadData() {
    }

    override fun initListener() {
    }
}