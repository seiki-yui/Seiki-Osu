package org.seiki.plugin

import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.globalEventChannel
import net.mamoe.mirai.event.subscribeMessages
import net.mamoe.mirai.utils.ExternalResource
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import net.mamoe.mirai.utils.info
import org.jetbrains.skia.Color
import org.jetbrains.skia.Paint
import org.jetbrains.skia.Surface
import org.jetbrains.skia.TextLine
import org.laolittle.plugin.Fonts
import org.laolittle.plugin.toExternalResource

val osuLogo = SeikiOsu::class.java.getResourceAsStream("/osuLogo.png").use { it!!.readAllBytes() }!!

object SeikiOsu : KotlinPlugin(
    JvmPluginDescription(
        id = "org.seiki.osu",
        name = "SeikiOsu",
        version = "1.1",
    ) {
        author("xiao-zheng233")
//        dependsOn("org.laolittle.plugin.SkikoMirai", ">=1.0.3", true)
    }
) {
    override fun onEnable() {
        logger.info { "Seiki Osu-Plugin loaded" }
        globalEventChannel().subscribeMessages {
            """\s*#\s*[oO][sS][uU]!?\s*(.{1,10})?\s*""".toRegex() findingReply {
                makeOsu(it.groupValues[1].ifEmpty { "osu!" }).use { r -> r.uploadAsImage(subject) }
            }
        }
    }
}


fun makeOsu(text: String = "osu!"): ExternalResource {
    val paint = Paint().apply {
        isAntiAlias = true
        color = Color.WHITE
    }
    val image =
        org.jetbrains.skia.Image.makeFromEncoded(osuLogo)
    var osuText = TextLine.make(text, Fonts["Aller-Bold", 112.5F])
    var yPos = 137.5F + osuText.height / 2
    if (osuText.width > 250) {
        yPos = 210 - (osuText.width - 255) / 20
        osuText = TextLine.make(text, Fonts["Aller-Bold", 250F / osuText.width * 112.5F])
    }
    val surface = Surface.makeRasterN32Premul(350, 350)
    surface.apply {
        canvas.apply {
            drawImage(image, 0F, 0F)
            drawTextLine(osuText, 175F - osuText.width / 2, yPos, paint)
        }
    }
    return surface.makeImageSnapshot().toExternalResource()
}