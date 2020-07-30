package com.wyq_github_pen_do.activity

import com.wyq.common.base.BaseActivity
import com.wyq_github_pen_do.R
import com.wyq_github_pen_do.databinding.ActivityMainBinding
import com.wyq_github_pen_do.fragment.MainFragment

/**
 * 主页
 *
 * todo 状态栏的显示, 内存泄漏, 换肤
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutId: Int = R.layout.activity_main

    override fun initView() {
        supportFragmentManager.beginTransaction().add(R.id.main_container,MainFragment(),"main_fragment").commitAllowingStateLoss()
    }

    override fun loadData() {

    }


}