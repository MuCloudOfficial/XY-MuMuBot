package me.mucloud.miraiplugin.XY.MuMuBot.module.mcmod

data class ModInfo(
    // 主ID | 在 MCMOD 上的 ID
    private val classID: Long
) {

    private lateinit var modState: Pair<String, String>
    private lateinit var modName: String
    private lateinit var platformsAndVersions: Map<String, List<String>>
    private lateinit var engid: String
    private lateinit var authors: List<String>
    private lateinit var dependencies: List<ModInfo>
    private lateinit var downloadLink: List<ModDownloadLinkInfo>

    fun getName(): String = modName

    fun toDependenceModInfo(id: Long) {

    }

    override fun toString(): String =
        """
            
        """.trimIndent()

}

data class ModDownloadLinkInfo(
    private val dest: String,
    private val desc: String,
    private val link: String,
){

}