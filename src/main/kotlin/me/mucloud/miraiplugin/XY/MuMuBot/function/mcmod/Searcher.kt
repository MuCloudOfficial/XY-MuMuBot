package me.mucloud.miraiplugin.XY.MuMuBot.function.mcmod

import net.mamoe.mirai.contact.User

object Searcher {

    private val POOL = emptyMap<User, SearchResult>().toMutableMap()


    /**
     *
     * 启动一个查询线程，该查询会将一个关键词通过 search.mcmod.cn 返回一个具有一个或多个模组的列表
     *
     * @since SakuraOcean V1
     * @author Mu_Cloud
     */
    fun startSearch2MCMODThread(pattern: String){

    }



}