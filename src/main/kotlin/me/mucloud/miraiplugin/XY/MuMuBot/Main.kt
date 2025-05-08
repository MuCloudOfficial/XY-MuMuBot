package me.mucloud.miraiplugin.XY.MuMuBot

import me.mucloud.miraiplugin.XY.MuMuBot.module.ModuleManager
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info

internal object Main : KotlinPlugin(
    JvmPluginDescription(
        id = "me.mucloud.miraiplugin:xy.mumubot",
        name = "XY-MuMuBot",
        version = "3.1.1",
    ){
        author("Mu_Cloud")
        info("XY-MuMuBot")
    }
){

    init{
        ModuleManager.start()
    }

    override fun onEnable() {
        logger.info { """
            | WELCOME TO USE XY SERIES PLUGIN
            | MADE IN SAKURA OCEAN & BASED ON MIRAI
            | Copyright(c) 2019-2024, Twilight Cloud Pavilion, ALL RIGHTS RESERVED.
        """.trimIndent() }
    }

}