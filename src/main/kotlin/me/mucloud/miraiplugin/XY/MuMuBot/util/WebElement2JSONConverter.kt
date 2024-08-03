package me.mucloud.miraiplugin.XY.MuMuBot.util

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.net.URL

object WebElement2JSONConverter {

    private val SearchSource: String = "https://search.mcmod.cn/s?key={key}&filter=0&page={page}"
    private val MODINFOSource: String = "https://www.mcmod.cn/class/{cid}.html"

    fun getDoc(url: String): Document = Jsoup.parse(URL(url), 2000).also {
            it.charset(Charsets.UTF_8)
    }

    fun parseModInfoList2JSON(key: String, page: Int): JsonArray{
        val res = JsonArray()
        val doc = getDoc(SearchSource.replace("{key}",key).replace("{page}", page.toString()))

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

    fun parseModInfo2JSON(cid: Int): JsonObject{
        val res = JsonObject()
        val doc = getDoc(MODINFOSource.replace("{cid}", cid.toString()))

        // 定位大致元素块位置
        val tag = doc.getElementsByClass("common-class-category")[0]
        val title = doc.getElementsByClass("class-title")[0]
        val info = doc.getElementsByClass("class-info-left")[0].select("ul")[0].children()
        val dep = if (doc.getElementsByClass("class-relation-list").isEmpty()) {
            doc.empty()
        }else{
            doc.getElementsByClass("class-relation-list")
        }

        // 获取标题及更新、开源情况
        res.addProperty("cid", cid.toString())
        res.addProperty("shortName", title.getElementsByClass("short-name").text())
        res.addProperty("name", title.getElementsByTag("h3").text())
        res.addProperty("engName", title.getElementsByTag("h4").text())
        res.addProperty("status", title.child(0).child(0).text())
        res.addProperty("source", title.child(0).child(1).text())

        // 获取标签: 标签
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

        // 获取详细信息
        res.add("platform", JsonArray().also { ja ->
            info[0].children().forEach{
                ja.add(it.text())
            }
        })

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

        res.add("env", JsonObject().also {
            it.addProperty("client", info[2].text().split(" ")[1].dropLast(1))
            it.addProperty("server", info[2].text().split(" ")[2])
        })

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

        res.add("relations", JsonObject().also {

        })

        return res
    }

}