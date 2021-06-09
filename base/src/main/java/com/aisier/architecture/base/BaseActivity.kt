package com.aisier.architecture.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.aisier.architecture.manager.NetworkStateManager
import com.gyf.immersionbar.ImmersionBar

/**
 * <pre>
 * author : wutao
 * e-mail : ldlywt@163.com
 * time   : 2021/5/18
 * desc   :
 * version: 1.2
</pre> *
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBar()
        setContentView(layoutResId)
        init()
        initNetworkStateManager()
    }

    @get:LayoutRes
    protected abstract val layoutResId: Int

    protected abstract fun init()

    private fun initNetworkStateManager() {
        lifecycle.addObserver(NetworkStateManager)
    }

    protected fun setStatusBar() {
        ImmersionBar.with(this)
            .transparentStatusBar()
            .statusBarDarkFont(true)
            .init()
    }

    protected fun setStatusBarDark() =
        ImmersionBar.with(this).statusBarDarkFont(true).transparentBar().init()

}