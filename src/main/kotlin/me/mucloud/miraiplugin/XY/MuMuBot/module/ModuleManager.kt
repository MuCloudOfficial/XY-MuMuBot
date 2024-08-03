package me.mucloud.miraiplugin.XY.MuMuBot.module

import net.mamoe.mirai.console.permission.PermissionId

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
        if(module.reg()){
            module.open()
            ModulePool.add(module)
        }
    }

    fun startModule(module: Module){
        if(!module.isOpen()){
            module.open()
        }
    }

    object Permission: net.mamoe.mirai.console.permission.Permission{
        override val description: String = "当前插件所有模块的使用权"
        override val id: PermissionId = PermissionId("xymumubot","admin")
        override val parent: net.mamoe.mirai.console.permission.Permission = this
    }

}