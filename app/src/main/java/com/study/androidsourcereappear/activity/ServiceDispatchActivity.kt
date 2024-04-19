package com.study.androidsourcereappear.activity

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.study.androidsourcereappear.R
import com.study.androidsourcereappear.databinding.ActivityServiceDispatchBinding
import com.study.androidsourcereappear.service.DownloadService
import com.study.androidsourcereappear.service.MusicService


class ServiceDispatchActivity : AppCompatActivity() {
  private val className = this.javaClass.simpleName

  private val binding
          by lazy { ActivityServiceDispatchBinding.inflate(layoutInflater) }

  private var downloadDialog: AlertDialog? = null

  // 绑定的MusicService实例
  private lateinit var mMusicService: MusicService

  // 是否已绑定service的标志位
  private var mBound: Boolean = false
  private val connection = object : ServiceConnection {
    // 服务已创建完成，通信连接已建立完成，Service对应的Binder实例入参，然后回调该方法
    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
      mMusicService = (service as MusicService.MusicBinder).service
      mBound = true

      Log.d(className, "与${name} 连接建立")
    }

    override fun onServiceDisconnected(name: ComponentName?) {
      mBound = false
      Log.d(className, "与$name 连接断开")
    }
  }

  private val downloadReceiver = object : BroadcastReceiver() {
    private var progress = 0
    override fun onReceive(context: Context?, intent: Intent?) {
      // 更新下载进度
      if (intent != null) {
        progress = intent.getIntExtra("progress", progress)
      }
      downloadDialog?.apply {
        findViewById<ProgressBar>(R.id.pb_download_process)?.progress = progress
        findViewById<TextView>(R.id.tv_progress)?.text = "$progress%"
      }
      if (progress == 100) {
        Toast.makeText(this@ServiceDispatchActivity, "下载完成", Toast.LENGTH_SHORT).show()
        //关闭进度条
        downloadDialog?.dismiss()
      }
    }
  }

  @SuppressLint("UnspecifiedRegisterReceiverFlag")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)

    initUI()
    // 动态注册 广播接收器
    val filter = IntentFilter().also { it.addAction("www.down.redrock.edu") }
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) // API 33
        registerReceiver(downloadReceiver, filter, RECEIVER_EXPORTED)
      else
        registerReceiver(downloadReceiver, filter)

  }

  override fun onDestroy() {
    super.onDestroy()
    unregisterReceiver(downloadReceiver)
    // 解绑
    unbindService(connection)
    mBound = false
  }

  private fun initUI() {
    binding.btnStartMusic.setOnClickListener {
      // 两种方式同时启动服务
      startService(Intent(this, MusicService::class.java))
      Intent(this, MusicService::class.java).also { intent ->
        bindService(intent, connection, BIND_AUTO_CREATE)
      }
    }

    binding.btnStartDownload.setOnClickListener {
      getDialogWithCustomView("任务Z").show()
      startService(
        Intent(this, DownloadService::class.java).putExtra(
          "url",
          "http://www.baidu.com/xxx.txt"
        )
      )
    }

    binding.btnClickControl.setOnClickListener {
      if (!mBound) return@setOnClickListener
      if (mMusicService.isPlaying()) {
        // 此时点击后应该暂停了播放
        mMusicService.pause()
        (it as ImageButton).setImageResource(R.drawable.ic_click_play)
      } else {
        // 此时点击后应该开始（或继续）播放
        mMusicService.play()
        (it as ImageButton).setImageResource(R.drawable.ic_click_pause)
      }
    }
  }

  private fun getDialogWithCustomView(itemName: String = "任务X"): AlertDialog {
    return downloadDialog.also { it?.show() } ?: MaterialAlertDialogBuilder(this)
      .setCancelable(false)
      .setTitle("${itemName}下载中……")
      .setPositiveButton("隐藏") { dialog, _ ->
        (dialog as AlertDialog).hide()
      }
      .setNegativeButton("关闭") { dialog, _ -> dialog.dismiss() }
      .setView(R.layout.view_progress)
      .show().also {
        downloadDialog = it
      }
  }
}