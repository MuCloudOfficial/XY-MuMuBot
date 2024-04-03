package me.mucloud.miraiplugin.XY.MuMuBot.module.JoinMessage

import me.mucloud.miraiplugin.XY.MuMuBot.module.ModuleManager
import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.ValueName
import net.mamoe.mirai.console.data.value
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.Listener
import net.mamoe.mirai.event.events.MemberJoinEvent
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.message.data.buildMessageChain

object JoinMessage {

    private var Listener: Listener<MemberJoinEvent>? = null

    init {
        if(ModuleManager.JoinMessage){
            Listener = GlobalEventChannel.subscribeAlways<MemberJoinEvent> { event ->
                if(ModuleConfig.gs.contains(event.groupId)){
                    group.sendMessage(buildMessageChain {
                        +At(user)
                        ModuleConfig.jm.forEach{
                            +PlainText(it)
                        }
                    })
                }
            }
        }
    }

    fun close(){
        Listener?.complete()
    }

    object ModuleConfig: AutoSavePluginConfig("JoinMessage"){

        @ValueName("JoinMessage")
        @ValueDescription("定义新成员进群时的欢迎消息, 允许多行")
        val jm: List<String> by value()

        @ValueName("AllocatedGroups")
        @ValueDescription("被允许使用该功能的群, 允许多个")
        val gs: List<Long> by value()
    }
}
