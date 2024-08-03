package me.mucloud.miraiplugin.XY.MuMuBot.module.mcmod

import net.mamoe.mirai.contact.User
import net.mamoe.mirai.contact.UserOrBot

class SearchThread(
    private val target: UserOrBot,
    private val pattern: String
){

    private var total: Int
    private var page: Int

    init {
        total = 0
        page = 1
        sendView()
    }

    fun nextPage(){
        page++
        sendView()
    }

    fun prevPage(){
        if(page != 0) {
            page--
            sendView()
        }
    }

    private fun sendView(){

    }

    private fun getSearchList(pattern: String): List<ModInfo>{
        return emptyList()
    }

    private fun getModInfo(cid: Int): ModInfo {
        TODO()
    }

    fun equals(target: User): Boolean{
        return target == this.target
    }

    override fun hashCode(): Int {
        return target.hashCode()
    }

}