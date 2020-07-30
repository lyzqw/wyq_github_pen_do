package com.wyq.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<ViewDB : ViewDataBinding> : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ViewDB>(this, layoutId)
        binding.lifecycleOwner = this

        initView()
        loadData()
    }

    abstract val layoutId: Int

    abstract fun initView()

    abstract fun loadData()

}