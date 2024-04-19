package com.study.androidsourcereappear.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import kotlin.concurrent.thread

class DownloadService : Service() {
  private val className = this.javaClass.simpleName
  private val binder by lazy { DownloadBinder() }

  // 标志位, 是否有任务正在下载
  private var isRunning = false
  override fun onCreate() {
    super.onCreate()

    Log.d(className, "onCreate: 在一个Service实例中只会执行一次的方法")
//    thread {
//      for (i in 1..10) {
//        Intent().setAction("www.down.redrock.edu")
//          .putExtra("progress", 10 * i).also {
//            // 休息1.5秒, 模拟下载过程
//            Thread.sleep(1500)
//            sendBroadcast(it)
//          }
//      }
//      // 下载任务完成了, 该Service实例就可以销毁了
//      stopSelf()
//    }
  }

  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    val url = intent?.getStringExtra("url")
    Log.d(className, "onStartCommand: 收到了前往$url 的下载任务")
    if (!isRunning) {
      isRunning = true
      thread {
        for (i in 1..10) {
          Intent().setAction("www.down.redrock.edu")
            .putExtra("progress", 10 * i).also {
              // 休息1.5秒, 模拟下载过程
              Thread.sleep(1500)
              sendBroadcast(it)
            }
        }
        // 下载任务完成了, 该Service实例就可以销毁了
        stopSelf()
      }
    }

    return super.onStartCommand(intent, flags, startId)
  }

  override fun onUnbind(intent: Intent?): Boolean {
    Log.d(className, "onUnbind: $className 被解绑了")
    return super.onUnbind(intent)
  }

  override fun onBind(intent: Intent): IBinder = binder

  override fun onDestroy() {
    super.onDestroy()
    Log.d(className, "onDestroy: 下好了，我也该寄了")
  }


  inner class DownloadBinder : Binder() {
    val service: DownloadService get() = this@DownloadService
  }
}