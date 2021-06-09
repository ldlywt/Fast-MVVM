package com.aisier.activity

import android.util.Log
import androidx.lifecycle.observe
import com.aisier.R
import com.aisier.architecture.base.BaseActivity
import com.aisier.architecture.util.toast
import com.aisier.architecture.util.viewBinding
import com.aisier.bean.UserBean
import com.aisier.databinding.ActivitySecondBinding
import com.aisier.util.TimerShareLiveData
import com.aisier.util.UserCacheLiveData

class SecondActivity : BaseActivity() {

    private val mBinding by viewBinding(ActivitySecondBinding::bind)

    override val layoutResId: Int
        get() = R.layout.activity_second

    override fun init() {
        TimerShareLiveData.get(MainActivity::class.simpleName).observe(this) {
            Log.i("wutao--> ", "SecondActivity: $it")
        }

        mBinding.btGetUserInfo.setOnClickListener {
            TimerShareLiveData.get().cancelTimer()
            val user = UserBean("张三", (Math.random() * 1000).toLong())
            UserCacheLiveData.cacheUser(user)
            toast("发消息给第一个页面：" + user.toString())
        }
    }

}