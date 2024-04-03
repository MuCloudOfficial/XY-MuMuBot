package me.mucloud.miraiplugin.XY.MuMuBot.module.mcmod

import net.mamoe.mirai.contact.UserOrBot

class SearchThread(
    private val target: UserOrBot,
    private val pattern: String
){

    private var page: Int

    init {
        page = 0
    }

    suspend fun ModSearch(pattern: String, page: Int){

    }

}