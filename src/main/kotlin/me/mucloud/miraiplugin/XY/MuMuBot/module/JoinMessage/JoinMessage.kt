package me.mucloud.miraiplugin.XY.MuMuBot.module.JoinMessage

import me.mucloud.miraiplugin.XY.MuMuBot.Main.reload
import me.mucloud.miraiplugin.XY.MuMuBot.module.Module
import net.mamoe.mirai.console.command.ConsoleCommandSender
import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.ValueName
import net.mamoe.mirai.console.data.value
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.Listener
import net.mamoe.mirai.event.events.MemberJoinEvent
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.message.data.buildMessageChain

object JoinMessage: Module(
    "JoinMessage",
    "JoinMessage Module"
) {

    private var Listener: Listener<MemberJoinEvent>? = null

    override fun whenOpen() {
        if(ModuleConfig.gs.isEmpty()){
            suspend{ ConsoleCommandSender.sendMessage("当前没有组被列入欢迎区，已被关闭") }
            return
        }
        Listener = GlobalEventChannel.subscribeAlways<MemberJoinEvent> { event ->
            if(ModuleConfig.gs.contains(event.groupId)){
                group.sendMessage(buildMessageChain {
                    +Image("joinGroupImage") {}
                    +At(user)
                    ModuleConfig.jm.forEach{
                        +PlainText(it)
                    }
                })
            }
        }
    }

    override fun whenClose(){
        Listener?.complete()
    }

    object ModuleConfig: AutoSavePluginConfig(name){
        @ValueName("msg")
        @ValueDescription("定义新成员进群时的欢迎消息, 允许多行")
        val jm: List<String> by value()

        @ValueName("groups")
        @ValueDescription("被允许使用该功能的群, 允许多个")
        val gs: List<Long> by value()
    }

}
