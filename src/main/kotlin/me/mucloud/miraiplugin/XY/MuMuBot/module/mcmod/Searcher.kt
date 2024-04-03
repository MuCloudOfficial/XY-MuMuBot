package me.mucloud.miraiplugin.XY.MuMuBot.module.mcmod

import net.mamoe.mirai.contact.UserOrBot

object Searcher {

    private val POOL = emptyList<SearchThread>().toMutableList()


    /**
     *
     * 启动一个查询线程，该查询会将一个关键词通过 search.mcmod.cn 返回一个具有一个或多个模组的列表
     *
     * @since SakuraOcean V1
     * @author Mu_Cloud
     */
    fun createSearchThread(user: UserOrBot, pattern: String, page: Int){

    }



}