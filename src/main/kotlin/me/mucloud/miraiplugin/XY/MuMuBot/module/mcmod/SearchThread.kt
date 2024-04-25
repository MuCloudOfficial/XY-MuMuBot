package me.mucloud.miraiplugin.XY.MuMuBot.module.mcmod

import me.mucloud.miraiplugin.XY.MuMuBot.util.WebElement2JSONConverter
import net.mamoe.mirai.contact.UserOrBot
import java.net.URL

class SearchThread(
    private val target: UserOrBot,
    private val pattern: String
){

    private var page: Int

    init {
        page = 1
        sendView()
    }

    fun nextPage(){
        page++
    }

    fun prevPage(){
        if(page != 0) {
            page--
            sendView()
        }
    }

    private fun sendView(){

    }

    private fun getSearchList(pattern: String, page: Int): List<ModInfo>{
        TODO()
    }

    private fun getModInfo(cid: Int): ModInfo{
        TODO()
    }



}