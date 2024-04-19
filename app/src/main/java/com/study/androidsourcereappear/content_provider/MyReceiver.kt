package com.study.androidsourcereappear.content_provider

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

  override fun onReceive(context: Context, intent: Intent) {
    Toast.makeText(context, "MyReceiver 收到广播", Toast.LENGTH_SHORT).show()
  }
}