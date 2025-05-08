package me.mucloud.miraiplugin.XY.MuMuBot.module.mcmod

import net.mamoe.mirai.contact.UserOrBot
import java.util.*
import kotlin.concurrent.schedule

class SearchThread(
    private val target: UserOrBot,
    private val pattern: String,
    private val interval: Int,
){

    private val tsk = Timer().schedule(interval * 1000L, 0){

    }

    private var total: Int
    private var page: Int

    init {
        total = 0
        page = 1
    }

}