package kr.sul.dynmapaddition.grave

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.dynmap.bukkit.DynmapPlugin

object GraveMarkerSet {
    private const val LAYER_NAME = "Graves"
    private val dynmapPlugin = DynmapPlugin.plugin
    private val markerSet = run {
        val markerAPI = dynmapPlugin.markerAPI
        var markerSet = markerAPI.getMarkerSet(LAYER_NAME.toLowerCase())
        if (markerSet == null) {
            markerSet = markerAPI.createMarkerSet(LAYER_NAME.toLowerCase(), LAYER_NAME, null, true) // id, label, iconlimit(null = 모두허용), persistent
        }
        markerSet.hideByDefault = false
        markerSet.layerPriority = 100
        markerSet.minZoom = 3
        markerSet
    }
    private val graveIcon = dynmapPlugin.markerAPI.getMarkerIcon("skull")

    fun createGraveMarker(p: Player, validityTerm: Int) {
        val loc = p.location
        // (id, label, world.name, x, y, z, icon, true)
        // (id, label, true(markup - default:false), world.name, x, y, z, icon, false);
        // - markup - if true, label is HTML markup
        // - is_persistent - 리붓시 지속여부 = 저장
        markerSet.createMarker("${System.currentTimeMillis() + validityTerm*1000}", p.name, loc.world.name, loc.x, loc.y, loc.z, graveIcon, false)
    }

    fun removeExpiredMarkers() {
        val currentTimeMillis = System.currentTimeMillis()
        for (marker in markerSet.markers) {
            try {
                val markerExpireTime = marker.markerID.toLong()
                Bukkit.getServer().broadcastMessage("$currentTimeMillis > $markerExpireTime")
                if (currentTimeMillis > markerExpireTime) {
                    marker.deleteMarker()
                }
            } catch (ignored: Exception) {}
        }
    }
    fun removeAllMarkers() {
        markerSet.markers.forEach { it.deleteMarker() }
    }
}