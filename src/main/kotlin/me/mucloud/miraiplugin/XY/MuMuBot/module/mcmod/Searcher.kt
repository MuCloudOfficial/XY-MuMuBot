package me.mucloud.miraiplugin.XY.MuMuBot.module.mcmod

import me.mucloud.miraiplugin.XY.MuMuBot.module.Module
import net.mamoe.mirai.contact.UserOrBot

object Searcher: Module{

    private val POOL = emptyList<SearchThread>().toMutableList()

    override var open: Boolean = false
    override val info: String = ""

    /**
     *
     * 启动一个查询线程，该查询会将一个关键词通过 search.mcmod.cn 返回一个具有一个或多个模组的列表
     *
     * @since SakuraOcean V1
     * @author Mu_Cloud
     */
    fun createSearchThread(user: UserOrBot, pattern: String, page: Int){

    }

    override fun saveConfig() {
        TODO("Not yet implemented")
    }

}