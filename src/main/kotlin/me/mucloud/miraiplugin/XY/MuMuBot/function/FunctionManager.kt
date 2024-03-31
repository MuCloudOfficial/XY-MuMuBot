package me.mucloud.miraiplugin.XY.MuMuBot.function

import me.mucloud.me.mucloud.miraiplugin.XY.MuMuBot.Main.save
import me.mucloud.miraiplugin.XY.MuMuBot.function.JoinMessage.JoinMessage
import me.mucloud.miraiplugin.XY.MuMuBot.function.mcmod.Searcher
import me.mucloud.miraiplugin.XY.MuMuBot.function.mcserver.ServerBatchGenerator
import me.mucloud.miraiplugin.XY.MuMuBot.function.monitor.MonitorManager

object FunctionManager {

    // internal val Internal: Boolean = true
    internal val JoinMessage: Boolean = false
    internal val MCMODSearcher: Boolean = false
    internal val ServerBatchGenerator: Boolean = false
    internal val Monitor: Boolean = false

    init{
        me.mucloud.miraiplugin.XY.MuMuBot.function.JoinMessage.JoinMessage.ModuleConfig.save()

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