package me.mucloud.miraiplugin.XY.MuMuBot.module.mcmod

data class ModInfo(
    private val classID: Long,
    private val modState: Pair<String, String>,
    private val modName: String,
    private val engid: String,
    private val authors: List<String>,
    private val dependencies: List<ModDependenciesInfo>,
    private val downloadLink: List<ModDownloadLinkInfo>,
) {

    fun getName(): String = modName

    fun toDependenceModInfo(id: Long){

    }

    override fun toString(): String =
        """
            
        """.trimIndent()

}

data class ModDependenciesInfo(
    private val version: String,
    private val dependencies: List<String>,
    private val useToDepends: List<String>,
){

}

data class ModDownloadLinkInfo(
    private val dest: String,
    private val desc: String,
    private val link: String,
){

}