package me.mucloud.miraiplugin.XY.MuMuBot.internal

import me.mucloud.me.mucloud.miraiplugin.XY.MuMuBot.Main
import net.mamoe.mirai.console.command.CommandContext
import net.mamoe.mirai.console.command.CompositeCommand
import net.mamoe.mirai.console.command.isUser
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.message.data.buildMessageChain

object PluginInfo {

    private val info: String = """
        XY-MuMuBot | Mu_Mu's Bot | 暮暮机器人
        基于 Mirai Console 制作的 QQ 机器人插件
        当前版本: ${VersionChecker.getInternalVersion()}
        项目代码站: https://github.com/MuCloudOfficial/XY-MuMuBot
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
            context.sender.sendMessage(info)
        }

        @SubCommand("modules") suspend fun cmd_modules(context: CommandContext){
            val target = context.sender
            context.sender.sendMessage(buildMessageChain {
                if(target.isUser()){ +At(target.user) }
                +PlainText("当前已注册的模块:")
            })
        }

        @SubCommand("help") suspend fun cmd_help(context: CommandContext, module: String){
            context.sender.sendMessage("""
                
            """.trimIndent())
        }

    }

}