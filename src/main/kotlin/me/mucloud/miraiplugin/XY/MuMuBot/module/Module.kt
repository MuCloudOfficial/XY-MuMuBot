package me.mucloud.miraiplugin.XY.MuMuBot.module

import me.mucloud.miraiplugin.XY.MuMuBot.Main

interface IModule{
    fun open()
    fun close()
    fun whenOpen()
    fun whenClose()
    fun isOpen(): Boolean
}

abstract class Module(
    val name: String,
    val desc: String,
): IModule{

    init{
        ModuleManager.regModule(this)
    }

    private var isOpen: Boolean = false

    final override fun open(){
        if(!isOpen){ Main.logger.info("模块 $name 正在启动"); whenOpen(); isOpen = true; Main.logger.info("模块 $name 已启动")}
    }

    final override fun close(){
        if(isOpen){ Main.logger.info("模块 $name 正在关闭"); whenClose(); isOpen = false; Main.logger.info("模块 $name 已关闭") }
    }

    final override fun isOpen(): Boolean = isOpen

}