package com.aisier

import android.os.Bundle
import androidx.fragment.app.activityViewModels
import com.aisier.architecture.anno.FragmentConfiguration
import com.aisier.architecture.base.BaseFragment
import com.aisier.architecture.util.viewBinding
import com.aisier.databinding.FragmentMainBinding

/**
 * <pre>
 * @author : wutao
 * e-mail : 670831931@qq.com
 * time   : 2020/03/13
 * desc   :
 * version: 1.0
</pre> *
 */
@FragmentConfiguration(shareViewModel = true)
class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val mBinding by viewBinding(FragmentMainBinding::bind)

    private val mViewModel by activityViewModels<MainViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel.resultLiveData.observe(viewLifecycleOwner) {
            mBinding.text.text =
                it[0].showName + "   是否展示： " + it[0].isShow + "\n" + it[1].showName + "   是否展示： " + it[1].isShow
        }
    }

}