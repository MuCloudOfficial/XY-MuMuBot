package me.mucloud.miraiplugin.XY.MuMuBot.module.mcmod

import me.mucloud.miraiplugin.XY.MuMuBot.module.Module
import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.contact.User

object Searcher: Module(
    "MODSearcher",
    "MCMOD.cn Searcher"
){

    private val POOL = mutableListOf<SearchThread>()

    override fun whenOpen() {

    }

    override fun whenClose() {

    }

    /**
     *
     * 启动一个查询线程，该查询会将一个关键词通过 search.mcmod.cn 返回一个具有一个或多个模组的列表
     *
     * @since SakuraOcean V1
     * @author Mu_Cloud
     */
    fun createSearchThread(target: User, pattern: String, page: Int){

    }

    fun terminateSearchThread(target: User){

    }

    object ModuleConfig: AutoSavePluginConfig(name){

    }

}