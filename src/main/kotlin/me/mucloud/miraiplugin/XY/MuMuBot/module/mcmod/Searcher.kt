package me.mucloud.miraiplugin.XY.MuMuBot.module.mcmod

import me.mucloud.miraiplugin.XY.MuMuBot.module.Module
import me.mucloud.miraiplugin.XY.MuMuBot.module.ModuleManager
import net.mamoe.mirai.console.permission.PermissionId
import net.mamoe.mirai.contact.User

object Searcher: Module {

    private val POOL = emptyList<SearchThread>().toMutableList()

    override var open: Boolean = false
    override val info: String = ""

    init{ ModuleManager.regModule(this) }

    override fun reg(): Boolean {
        return true
    }

    override fun open() {
        super.open()
    }

    override fun close() {
        super.close()
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

    fun getNextView(target: User){

    }

    fun getPrevView(target: User){

    }

    object

    object Permission: net.mamoe.mirai.console.permission.Permission{
        override val description: String = "当前模块的使用权"
        override val id: PermissionId = PermissionId("xymumubot", "modsearcher")
        override val parent: net.mamoe.mirai.console.permission.Permission = ModuleManager.Permission
    }

}