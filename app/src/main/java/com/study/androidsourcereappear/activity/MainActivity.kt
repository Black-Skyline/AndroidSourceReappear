package com.study.androidsourcereappear.activity

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.study.androidsourcereappear.content_provider.MyReceiver
import com.study.androidsourcereappear.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val className = this.javaClass.simpleName
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // 给按钮设置点击监听
        binding.btnMainActivityGoto.setOnClickListener {
            startActivity(Intent(this, OtherActivity::class.java))
        }
        binding.btnMainSendBroadcast.setOnClickListener {
            sendBroadcast(Intent(this, MyReceiver::class.java))
        }

        Log.d(className, "onCreate: finished")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(className, "onRestart: finished")
    }

    override fun onStart() {
        super.onStart()

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