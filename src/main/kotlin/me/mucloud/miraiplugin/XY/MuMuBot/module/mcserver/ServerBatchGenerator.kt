package me.mucloud.miraiplugin.XY.MuMuBot.module.mcserver

import me.mucloud.me.mucloud.miraiplugin.XY.MuMuBot.Main
import me.mucloud.miraiplugin.XY.MuMuBot.module.Module
import net.mamoe.mirai.console.command.CommandContext
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.console.command.isUser
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.message.data.buildMessageChain
import java.io.File

object ServerBatchGenerator: Module{

    override var open: Boolean = false
    override val info: String = ""

    fun gen(java: String, useJDK: Boolean, min: Int, max: Int, core: String, extend: Boolean): String = """
        CHCP 65001
        @ECHO OFF
        REM AUTOMATIC GENERATE BY MUMUBOT
        REM MADE IN SAKURA OCEAN BASED ON BATCH SHELL
        ECHO 欢迎使用MC服务器启动脚本，内容由 Mu_Mu BOT 自动生成
        ECHO MADE IN SAKURA OCEAN BASED ON BATCH SHELL
        ECHO eula=true > eula.txt
        ECHO 准备启动服务器...
        PING 127.0.0.1 -n 10 >NUL
        ${if(extend){"""
        SET RC=0
        :START
        TITLE 服务器正在运行...     重启次数:%RC%
        """
        }else{"""
        TITLE 服务器正在运行...
        """
        }}
        ${if(java.matches(Regex("^[0-9]{1,2}|^1.8"))){"\"C:\\Program Files\\Java\\${if(useJDK){"jdk"}else{"jre"}}-$java\\bin\\java.exe\""}else{
            File(java).absolutePath}} ${if(useJDK){"-server"}else{""}} -Xms${min}M -Xmx${max}M -XX:+UseG1GC -XX:+ParallelRefProcEnabled -XX:MaxGCPauseMillis=200 -XX:+UnlockExperimentalVMOptions -XX:+DisableExplicitGC -XX:+AlwaysPreTouch -XX:G1NewSizePercent=30 -XX:G1MaxNewSizePercent=40 -XX:G1HeapRegionSize=8M -XX:G1ReservePercent=20 -XX:G1HeapWastePercent=5 -XX:G1MixedGCCountTarget=4 -XX:InitiatingHeapOccupancyPercent=15 -XX:G1MixedGCLiveThresholdPercent=90 -XX:G1RSetUpdatingPauseTimePercent=5 -XX:SurvivorRatio=32 -XX:+PerfDisableSharedMem -XX:MaxTenuringThreshold=1 -Dusing.aikars.flags=https://mcflags.emc.gs -Daikars.new.flags=true -jar $core --nogui
        PING 127.0.0.1 -n 5 >NUL
        cls
        ${if(extend){"""
        TITLE 服务器已停止...     重启次数:%RC%
        ECHO 服务器停止了！即将在10秒后执行重启！如果正在进行维护或更改设置文件，则直接关闭当前控制台！
        SET /a RC=%RC%+1
        PING 127.0.0.1 -n 10 >NUL
        GOTO START
        """}else{"""
        TITLE 服务器已停止...
        ECHO 服务器停止了！
        PAUSE>NUL
        """
        }}
        """.trimIndent()

    fun HelpDOC(): String = """
        /genbat4mcs [java] [jdk] [min] [max] [core.jar] [extend]
        生成对MC服务器启动的脚本Batch文件，暂时不支持生成Shell文件
        (该指令所有参数都是必填项)
        
        参数:
        java - java.exe的完整路径，如果你的java是以默认位置安装的填Java大版本数字，例如：8
        jdk - 安装的是否是JDK，如果是则写true，否则填false
        min - 最小内存量，以MB计
        max - 最大内存量，以MB计
        core.jar - 服务端核心文件全名
        extend - 是否使用额外美化，例如自动启停
    """.trimIndent()

    object ModuleCommand: SimpleCommand(
        Main,
        "mc服务器脚本生成",
        "genbat4mcs",
        description = "生成 MC 服务器启动脚本"
    ){

        /**
         *
         * # | 模块相关 > 功能
         *
         * ### MC 服务器启动代码生成器模块
         *
         *  指令：/genbat4mcs (java.exe的完整路径) (是否使用了JDK) (最小内存量/MB) (最大内存量/MB) (核心文件名.jar) (是否需要额外优化，例如自动启停等功能)
         *
         */
        @Handler suspend fun onCommand(context: CommandContext, java: String, useJDK: Boolean, min: Int, max: Int, core: String, extend: Boolean) {
            val target = context.sender
            target.sendMessage(buildMessageChain {
                if(target.isUser()){ +At(target.user) }
                +PlainText("已经生成，请直接粘贴分割线以下的内容并粘贴进bat文件中")
                +PlainText("注意在使用时查看控制台输出以确认自动生成脚本存在的问题")
                +PlainText("请注意该脚本已在运行时自动确认了EULA协议")
                +PlainText(gen(java, useJDK, min, max, core, extend))
            })
        }

    }

    @Deprecated("已废弃 | 该模块无设置项", ReplaceWith("TODO()"))
    override fun saveConfig() = TODO()


}