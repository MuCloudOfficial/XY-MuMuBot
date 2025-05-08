package me.mucloud.miraiplugin.XY.MuMuBot.util

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.net.URL

object MCMODCNInfoFetcher {

    private const val SEARCH_SOURCE: String = "https://search.mcmod.cn/s?key={key}&filter=0&page={page}"
    private const val MODINFO_SOURCE: String = "https://www.mcmod.cn/class/{cid}.html"

    private fun getDoc(url: String): Document = Jsoup.parse(URL(url), 2000).also {
            it.charset(Charsets.UTF_8)
    }

    fun getModNameByID(cid: Int): String{
        val doc = getDoc(MODINFO_SOURCE.replace("{cid}", cid.toString()))
        return doc.select(".common-nav>ul>li").last()!!.text()
    }

    // 根据CID获取模组相关链接
    fun parseModLinks2JSON(cid: Int): JsonArray{
        val doc = getDoc(MODINFO_SOURCE.replace("{cid}", cid.toString()))
        return JsonArray().also{ root ->
            doc.select(".common-link-icon-frame>li").forEach{ rawLink ->
                root.add(JsonObject().also { link ->
                    link.addProperty("title", rawLink.child(1).text())
                    link.addProperty("desc", rawLink.child(0).attr("data-original-title"))
                    link.addProperty("link", rawLink.child(0).attr("href"))
                })
            }
        }
    }

    // 根据关键词获取相关模组列表
    fun parseModInfoList2JSON(key: String, page: Int): JsonArray{
        val res = JsonArray()
        val doc = getDoc(SEARCH_SOURCE.replace("{key}",key).replace("{page}", page.toString()))

        doc.getElementsByClass("head").forEach{ e ->
            if(!e.getElementsByClass("class-category").isEmpty()){
                res.add(JsonObject().also{ j ->
                    // 获取 Class ID
                    j.addProperty("cid", e.child(1).attr("href").split("/").last().split(".")[0])

                    // 获取模组分类，如果没有则返回空值
                    val array = JsonArray()
                    e.getElementsByClass("class-category").select("a").forEach { le ->
                        array.add(le.className())
                    }
                    j.add("classes", array)

                    // 获取模组名与缩写
                    j.addProperty("name", e.child(1).text())
                })
            }
        }

        return res
    }

    // 根据CID获取模组信息
    fun parseModInfo2JSON(cid: Int): JsonObject{
        val res = JsonObject()
        val doc = getDoc(MODINFO_SOURCE.replace("{cid}", cid.toString()))

        // 定位大致元素块位置
        val tag = doc.getElementsByClass("common-class-category")[0]
        val title = doc.getElementsByClass("class-title")[0]
        val info = doc.getElementsByClass("class-info-left")[0].select("ul")[0].children()
        val dep = doc.getElementsByClass("class-relation-list").let { if(it.isEmpty()) null else it[0].children() }

        // 获取标题及更新、开源情况
        res.addProperty("cid", cid.toString())
        res.addProperty("shortName", title.getElementsByClass("short-name").text())
        res.addProperty("name", title.getElementsByTag("h3").text())
        res.addProperty("engName", title.getElementsByTag("h4").text())

        // 两个标签可能不会在网页上显示，不显示的则置为 Unknown
        res.addProperty("status", if(title.child(0).childrenSize() != 0) title.child(0).child(0).text() else title.child(0).text())
        res.addProperty("source", if(title.child(0).childrenSize() != 0) title.child(0).child(1).text() else "Unknown")

        // 获取标签: 标签, 子标签还有一堆小标签（怎么这么多标签？）
        // TODO("已经支持多重主标签，但实际网页上是否存在该结构还有待查验")
        res.add("tag", JsonObject().also { obj ->
            val main = JsonArray()
            val normal = JsonArray()
            val flag = JsonArray()
            tag.select("ul>li").forEach { e ->
                if(e.hasClass("main")){
                    main.add(e.selectFirst("a")?.attr("href")?.split("category=")?.get(1))
                }else{
                    normal.add(e.selectFirst("a")?.text())
                }
            }
            info[7].getElementsByTag("a").forEach {
                flag.add(it.text())
            }
            obj.add("main", main);obj.add("normal", normal);obj.add("flags", flag)
        })

        // 获取支持平台信息
        res.add("platform", JsonArray().also { ja ->
            info[0].children().forEach{
                ja.add(it.text())
            }
        })

        // 获取兼容的模组加载器和MC版本
        res.add("modded", JsonObject().also { jo ->
            val platform = emptyList<String>().toMutableList().also{ ja ->
                info[1].children().forEach {
                    ja.add("!${it.text()}")
                }
            }

            info[9].child(1).children().forEach{ ul ->
                val mod = JsonArray()
                var key = "Unknown"
                ul.children().forEach { li ->
                    val t = li.text()
                    if(li.elementSiblingIndex() == 0){
                        val p = t.dropLast(1)
                        key = if(platform.contains("!$p")){
                            platform.removeAt(platform.indexOf("!$p"))
                            p
                        }else{
                            "!$p"
                        }
                    }else{
                        if(li.hasClass("text-danger")){
                            mod.add("!${t}")
                        }else{
                            mod.add(t)
                        }
                    }
                }
                jo.add(key, mod)
            }

            platform.forEach{
                jo.addProperty(it, "null")
            }

        })

        // 获取改模组是否在服务器/客户端上运行
        res.add("env", JsonObject().also {
            it.addProperty("client", info[2].text().split(" ")[1].dropLast(1))
            it.addProperty("server", info[2].text().split(" ")[2])
        })

        // 获取 MCMOD 社区关于该模组的编辑信息
        res.addProperty("edits", info[4].text().split(" ")[1].dropLast(1))
        res.addProperty("createTime", info[3].attr("data-original-title"))
        res.addProperty("lastModifyTime", info[5].attr("data-original-title"))
        res.addProperty("lastRecommendTime", info[6].attr("data-original-title"))

        res.add("author", JsonArray().also { ja ->
            info[10].child(1).child(0).children().forEach{ li ->
                ja.add(JsonObject().also {
                    val member = li.getElementsByClass("member")[0]
                    it.addProperty("name", member.child(0).text())
                    it.addProperty("position", member.child(1).text())
                })
            }
        })

        // 获取该模组的依赖关系
        res.add("relations", JsonObject().also { relation ->
            dep?.drop(1)?.forEach{ e ->
                val set = e.getElementsByTag("legend").text()
                relation.add(set, JsonObject().also{ field ->
                    e.children().drop(1).forEach { sub ->
                        // 根据最后一个class名获取该子分组关系类别
                        val type = when(sub.selectFirst("span>i")!!.classNames().last()){
                            "fa-sitemap" -> "lib"
                            "fa-vector-square" -> "dep"
                            "fa-bezier-curve" -> "com"
                            else -> "Unknown"
                        }
                        field.add(type, JsonArray().also { mod ->
                            sub.select("ul>li>a").forEach { mod.add(it.attr("href").split("/").last().split(".")[0]) }
                        })
                    }
                })
            }
        })

        return res
    }

}