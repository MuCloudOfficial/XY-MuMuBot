package me.mucloud.miraiplugin.XY.MuMuBot

import net.mamoe.mirai.console.permission.PermissionId
import net.mamoe.mirai.console.permission.PermissionService
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info

object Main : KotlinPlugin(
    JvmPluginDescription(
        id = "me.mucloud.miraiplugin:xy.mumubot",
        name = "Mu_Mu's Bot",
        version = "1.0.1-SakuraOcean",
    ){
        author("Mu_Cloud")
        info("Mu_Mu's Bot")
    }
){

    init{

    }

    override fun onEnable() {
        logger.info { """
            | WELCOME TO USE XY SERIES PLUGIN
            | MADE IN SAKURA OCEAN & BASED ON MIRAI
            | Copyright(c) 2019-2024, Twilight Cloud Pavilion, ALL RIGHTS RESERVED.
        """.trimIndent() }

        PermissionService
    }

}