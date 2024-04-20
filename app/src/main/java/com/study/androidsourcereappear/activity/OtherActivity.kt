package com.study.androidsourcereappear.activity

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.study.androidsourcereappear.R
import com.study.androidsourcereappear.databinding.ActivityOtherBinding

class OtherActivity : AppCompatActivity() {

  private val className = this.javaClass.simpleName
  private val binding by lazy { ActivityOtherBinding.inflate(layoutInflater) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
    // 给按钮设置点击监听
    binding.btnOtherActivityGoto1.setOnClickListener {
      startActivity(Intent(this, FragmentContainerActivity::class.java))
    }
    binding.btnOtherActivityGoto2.setOnClickListener {
      startActivity(Intent(this, ServiceDispatchActivity::class.java))
    }

    Log.d(className, "onCreate: finished")
  }

  override fun onRestart() {
    super.onRestart()
    Log.d(className, "onRestart: finished")
  }

  override fun onStart() {
    super.onStart()
    Log.d(className, "onStart: begin")
//    try {
//      /// 设置一个启动延时，拉长 onStart() 的回调耗时
//      Thread.sleep(10000)
//    } catch (e: InterruptedException) {
//      Log.d(className, "onStartError: ${e.message}")
//    }

    Log.d(className, "onStart: finished")
  }

  override fun onResume() {
    super.onResume()

    Log.d(className, "onResume: finished")
  }

  override fun onPause() {
    super.onPause()

    Log.d(className, "onPause: finished")
  }

  override fun onStop() {
    super.onStop()

    Log.d(className, "onStop: finished")
  }

  override fun onDestroy() {
    super.onDestroy()

    Log.d(className, "onDestroy: finished")
  }

  override fun onConfigurationChanged(newConfig: Configuration) {
    super.onConfigurationChanged(newConfig)

    Log.d(className, "onConfigurationChanged: finished")
  }

}