package me.mucloud.miraiplugin.XY.MuMuBot.module

import me.mucloud.me.mucloud.miraiplugin.XY.MuMuBot.Main.save
import me.mucloud.miraiplugin.XY.MuMuBot.module.JoinMessage.JoinMessage
import me.mucloud.miraiplugin.XY.MuMuBot.module.mcmod.Searcher
import me.mucloud.miraiplugin.XY.MuMuBot.module.mcserver.ServerBatchGenerator
import me.mucloud.miraiplugin.XY.MuMuBot.module.monitor.MonitorManager

object ModuleManager {

    // internal val Internal: Boolean = true
    internal var JoinMessage: Boolean = false
    internal var MCMODSearcher: Boolean = false
    internal var ServerBatchGenerator: Boolean = false
    internal var Monitor: Boolean = false

    init{
        me.mucloud.miraiplugin.XY.MuMuBot.module.JoinMessage.JoinMessage.ModuleConfig.save()
    }

    fun init(){

    }

    fun JoinMessage.close(){

    }

    fun JoinMessage.open(){

    }

    fun Searcher.open(){

    }

    fun ServerBatchGenerator.open(){

    }

    fun MonitorManager.open(){

    }

}