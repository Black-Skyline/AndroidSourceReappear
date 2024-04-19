package com.study.androidsourcereappear.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.study.androidsourcereappear.R

class SecondFragment : Fragment() {
  private val className = this.javaClass.simpleName
  private var data: String? = null
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Log.d(className, "onCreate: 新建了一个 $className 实例")

  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_second, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    view.findViewById<Button>(R.id.btn_second_replace).setOnClickListener {
      Toast.makeText(requireActivity(), "这里的功能不重要，就不实现了", Toast.LENGTH_SHORT).show()
    }
    parentFragmentManager.setFragmentResultListener(
      "H57",
      this
    ) { key, bundle ->

      //这里是拿了String，但是其实其他类型的数据通过相应的方法都可以拿
      val result = bundle.getString("h57")
      Log.d(className, "onViewCreated: $key,data is $result")
      //拿到数据之后，将TextView的文本设置一下
      data = result ?: "怎么什么都没有啊"
      view.findViewById<TextView>(R.id.tv_second_data_show).text = data
    }
  }
}