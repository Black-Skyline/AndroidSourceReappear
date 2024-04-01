package scope

import org.gradle.api.Project
import org.jetbrains.annotations.NotNull

/**
 * @ClassName :   PluginScope
 * @Author :      Black-Skyline (Shujun Hu)
 * @Email :       2031649401@qq.com
 * @Date :        2024/3/23
 * @Version:      1.0
 * @Description : 仅暴露给BasePlugin，使用委托将Project的作用域共享，防止Project被子类持有引用后污染作用域
 */
class PluginScope(rawProject: Project): Project by rawProject