package com.study.androidsourcereappear.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import com.study.androidsourcereappear.R
import com.study.androidsourcereappear.databinding.FragmentFirstBinding

class FirstFragment : BaseVBFragment<FragmentFirstBinding>(FragmentFirstBinding::inflate) {
  private val className = this.javaClass.simpleName
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    Log.d(className, "onCreate: 新建了一个 $className 实例")
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initUI()

  }

  private fun initUI() {
    // replace 按钮的监听
    binding.btnFirstReplace.setOnClickListener {
      /// 1.拿到本fragment宿主的FragmentManager
      /// 由于本fragment是寄生在Activity上的, 也可以这么写来获取宿主的FragmentManager
      // val fragmentManager = requireActivity().supportFragmentManager
      val fragmentManager = parentFragmentManager
      /// 2.通过FragmentManager开启一个FragmentTransaction(事务)
      val fragmentTransaction = fragmentManager.beginTransaction()
      /// 3.获取一个需要展示的Fragment实例，容器里不存在的话，就新建一个
      val secondFragment = getFragment("SecondFragment的实例") {
        SecondFragment()
      }
      /// 4.第一个参数是ViewGroup(有些重载也可以放容器的id), 第二个参数是要新操作的fragment
      fragmentTransaction.replace(R.id.fl_container, secondFragment)
      // ***注意这里，将其加入返回栈中***
      // fragmentTransaction.addToBackStack(null) // 把事务添加到返回栈，这样点击返回之后就相当于是在回滚事务
      /// 5.事务的提交操作, 开启了一个事务之后一定要提交，只有调用提交的方法之后, 上面对事务的操作才能生效
      fragmentTransaction.commit()
      val data = "FirstFragment传来的数据"
      fragmentManager.setFragmentResult("H57", bundleOf("h57" to data))

      /// 以上的那一堆等价于下面的这四行
//      parentFragmentManager.commit {
//        replace<SecondFragment>(R.id.fl_container)
//        addToBackStack(null)
//      }
    }

    // add 按钮的监听
    binding.btnFirstAdd.setOnClickListener {
      parentFragmentManager.beginTransaction().apply {
        add(R.id.fl_container, SecondFragment(), "SecondFragment的实例")
        commit()
      }
    }

    // remove 按钮的监听
    binding.btnFirstRemove.setOnClickListener {
      parentFragmentManager.beginTransaction().apply {
        parentFragmentManager.findFragmentById(R.id.fl_container)?.also {
          remove(it)
        }
        commit()
      }
    }
  }
}