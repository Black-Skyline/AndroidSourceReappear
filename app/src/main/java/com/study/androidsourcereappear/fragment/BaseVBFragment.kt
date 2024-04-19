package com.study.androidsourcereappear.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KClass

/**
 * @ClassName :   BaseVBFragment
 * @Author :      Black-Skyline (Shujun Hu)
 * @Email :       2031649401@qq.com
 * @Date :        2024/4/16
 * @Version:      1.0
 * @Description : TODO
 */
open class BaseVBFragment<VB : ViewBinding>(private val inflateFunc: (LayoutInflater, ViewGroup?, Boolean) -> VB) :
  Fragment() {
  private var _binding: VB? = null
  /**
   * 注意：
   * 此处使用了了非空断言，故请在使用 [binding] 之前保证 [_binding] 已经被赋值
   */
  protected val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = this.inflateFunc(inflater, container, false)
    return binding.root
  }

  @Suppress("UNCHECKED_CAST")
  open fun <F: Fragment> getFragment(tag: String, newInstance: () -> F): F {
    return (parentFragmentManager.findFragmentByTag(tag)?: newInstance()) as F
  }

  @Suppress("UNCHECKED_CAST")
  open fun <F: Fragment> getFragment(containerId: Int, newInstance: () -> F): F {
    return (parentFragmentManager.findFragmentById(containerId)?: newInstance()) as F
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}