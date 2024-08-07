package me.mucloud.miraiplugin.XY.MuMuBot.module

interface Module {

    var open: Boolean
    val info: String

    fun reg(): Boolean

    fun open(){
        if(isOpen()){
            return
        }
        open = true
    }

    fun close(){
        if(isOpen()){
            open = false
        }
    }

    fun isOpen(): Boolean = open

}