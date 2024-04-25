package me.mucloud.miraiplugin.XY.MuMuBot.module

object ModuleManager {

    private var ModulePool = emptyList<Module>().toMutableList()

    /**
     *
     * # | 模块相关
     *
     * 注册一个模块
     *
     * 该模块必须实现 [Module] 接口
     *
     * @author Mu_Cloud
     * @since SakuraOcean V1
     */
    fun regModule(module: Module){
        ModulePool.add(module)
    }

    /**
     *
     * # | 模块相关
     *
     * 启动所有已注册的模块
     *
     * **注意：这并不能启动默认关闭或正在关闭状态的模块**
     *
     * @author Mu_Cloud
     * @since SakuraOcean V1
     *
     */
    fun startALLModule(){

    }

}