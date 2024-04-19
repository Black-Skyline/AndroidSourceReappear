package com.study.androidsourcereappear.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.study.androidsourcereappear.R
import com.study.androidsourcereappear.fragment.FirstFragment

class FragmentContainerActivity : AppCompatActivity() {
  private val className = this.javaClass.simpleName
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fragment_container)

    Log.d(className, "onCreate: fragment容器启动")

    /// 1.拿到本Activity的FragmentManager
    val fragmentManager = supportFragmentManager
    /// 2.通过FragmentManager开启一个FragmentTransaction(事务)
    val fragmentTransaction = fragmentManager.beginTransaction()
    /// 3.创建一个需要展示的Fragment实例
    val firstFragment = FirstFragment()
    /// 4.第一个参数是ViewGroup(有些重载也可以放容器的id)
    fragmentTransaction.add(R.id.fl_container, firstFragment)
    /// 5.事务的提交操作, 开启了一个事务之后一定要提交，只有调用提交的方法之后, 上面对事务的操作才能生效
    fragmentTransaction.commit()

  }

  override fun onDestroy() {
    super.onDestroy()

    Log.d(className, "onDestroy: 退出了 ${className}类")
  }
}