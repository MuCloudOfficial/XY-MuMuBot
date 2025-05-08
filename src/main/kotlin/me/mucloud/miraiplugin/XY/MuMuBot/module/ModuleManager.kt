package me.mucloud.miraiplugin.XY.MuMuBot.module

object ModuleManager {

    private var ModulePool = mutableListOf<Module>()

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
    internal fun regModule(module: Module){
        if(!module.isOpen()){
            module.open()
        }
    }

    fun start(){

    }

}