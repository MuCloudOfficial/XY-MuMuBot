package me.mucloud.miraiplugin.XY.MuMuBot.module.internal

import me.mucloud.me.mucloud.miraiplugin.XY.MuMuBot.Main
import net.mamoe.mirai.console.command.CommandContext
import net.mamoe.mirai.console.command.CompositeCommand

object PluginInfo {

    private val info: String = """
        
    """.trimIndent()

    object Command: CompositeCommand(
        Main,
        "mumubot",
        description = "Mu_Mu BOT 本体模块相关指令"
    ){

        /**
         *
         *  插件本体模块
         *
         *  指令： /mumubot
         *
         *  用于发送 Mu_Mu's Bot 自述消息
         *
         */
        @SubCommand("info") suspend fun cmd_info(context: CommandContext){

        }

        @SubCommand("modules") suspend fun cmd_modules(context: CommandContext){

        }

        @SubCommand("help") suspend fun cmd_help(context: CommandContext, module: String){

        }

    }

}