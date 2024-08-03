package me.mucloud.miraiplugin.XY.MuMuBot.module.JoinMessage

import me.mucloud.miraiplugin.XY.MuMuBot.Main.reload
import me.mucloud.miraiplugin.XY.MuMuBot.Main.save
import me.mucloud.miraiplugin.XY.MuMuBot.module.Module
import me.mucloud.miraiplugin.XY.MuMuBot.module.ModuleManager
import net.mamoe.mirai.console.command.ConsoleCommandSender
import net.mamoe.mirai.console.data.*
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.Listener
import net.mamoe.mirai.event.events.MemberJoinEvent
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.message.data.buildMessageChain

object JoinMessage: Module {

    private var Listener: Listener<MemberJoinEvent>? = null

    override var open: Boolean = false
    override val info: String = "用于设置进群欢迎消息的模块"

    init { ModuleManager.regModule(this) }

    override fun reg(): Boolean {
        if(JoinMessageModuleConfig.gs.isEmpty()){
            suspend{ ConsoleCommandSender.sendMessage("当前没有组被列入欢迎区，已被关闭") }
            return false
        }
        suspend{
            ConsoleCommandSender.sendMessage("正在注册 JoinMessage 模块")
            JoinMessageModuleConfig.reload()
        }
        return true
    }

    override fun open() {
        Listener = GlobalEventChannel.subscribeAlways<MemberJoinEvent> { event ->
            if(JoinMessageModuleConfig.gs.contains(event.groupId)){
                group.sendMessage(buildMessageChain {
                    +Image("joinGroupImage") {}
                    +At(user)
                    JoinMessageModuleConfig.jm.forEach{
                        +PlainText(it)
                    }
                })
            }
        }
        super.open()
    }

    override fun close(){
        Listener?.complete()
        super.close()
    }

    object JoinMessageModuleConfig: AutoSavePluginConfig("JoinMessage"){
        @ValueName("JoinMessage")
        @ValueDescription("定义新成员进群时的欢迎消息, 允许多行")
        val jm: List<String> by value()

        @ValueName("AllocatedGroups")
        @ValueDescription("被允许使用该功能的群, 允许多个")
        val gs: List<Long> by value()
    }

}
