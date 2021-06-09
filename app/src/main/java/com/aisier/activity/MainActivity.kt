package com.aisier.activity

import android.util.Log
import androidx.activity.viewModels
import com.aisier.MainFragment
import com.aisier.MainViewModel
import com.aisier.R
import com.aisier.architecture.base.BaseActivity
import com.aisier.architecture.util.go
import com.aisier.architecture.util.viewBinding
import com.aisier.databinding.ActivityMainBinding
import com.aisier.util.TimerShareLiveData
import com.aisier.util.UserCacheLiveData

class MainActivity : BaseActivity() {

    private val mViewModel by viewModels<MainViewModel>()

    private val mBinding by viewBinding(ActivityMainBinding::bind)

    override val layoutResId: Int
        get() = R.layout.activity_main


    override fun init() {
        initData()
        TimerShareLiveData.get(MainActivity::class.simpleName).observe(this) {
            Log.i("wutao--> ", "MainActivity: $it")
        }

        UserCacheLiveData.getCacheUserData().observe(this) {
            Log.i("wutao--> ", "MainActivity:User info $it")
            mBinding.tvContent.text = "第二个页面发过来的消息:User info $it"
        }
    }

    private fun initData() {
        mBinding.btnNet.setOnClickListener {
            Thread { mViewModel.requestNet() }.start()
        }
        mViewModel.resultLiveData.observe(this) {
            mBinding.tvContent.text = it.toString()
        }
        supportFragmentManager.beginTransaction().replace(R.id.fl_contain, MainFragment()).commit()

        mBinding.goSecondActivity.setOnClickListener {
            go<SecondActivity>()
        }
    }

}
