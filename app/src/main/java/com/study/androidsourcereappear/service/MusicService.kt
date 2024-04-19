package com.study.androidsourcereappear.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.study.androidsourcereappear.R

class MusicService : Service() {

  private val className = this.javaClass.simpleName
  private val player by lazy { MediaPlayer.create(this, R.raw.song1) }
  private val binder by lazy { MusicBinder() }

  override fun onCreate() {
    super.onCreate()
    INSTANCE = this

    Log.d(className, "onCreate: 在一个Service实例中只会执行一次的方法")
  }

  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    return super.onStartCommand(intent, flags, startId)
  }

  override fun onUnbind(intent: Intent?): Boolean {
    Log.d(className, "onUnbind: $className 被解绑了")
    return super.onUnbind(intent)
  }

  override fun onBind(intent: Intent): IBinder = binder

  /**
   * 在该生命周期回调中请一定要释放用不到的资源、解除引用
   */
  override fun onDestroy() {
    super.onDestroy()
    player.run {
      stop()
      release()
    }
    Log.d(className, "onDestroy: 完成了使命")
  }

  fun play() = player.start()


  fun pause() = player.pause()


  fun isPlaying() = player.isPlaying


  companion object {
    lateinit var INSTANCE: MusicService
      private set
  }

  inner class MusicBinder : Binder() {
    /**
     * 通过持有"所属服务的实例引用"的方式让 其他组件可以通过Service中的[onBind]回调返回的binder实例中的service引用 来使用该Service类中的逻辑
     *
     * 如果这个Binder类不是对应服务的内部类（或者是静态内部类）,使用如下写法：
     *
     * ```kotlin
     * private val service: MusicService = MusicService.INSTANCE
     * ```
     * 如果是自定义的Binder是对应Service的内部类，写法如下
     */
    val service: MusicService get() = this@MusicService
  }

}