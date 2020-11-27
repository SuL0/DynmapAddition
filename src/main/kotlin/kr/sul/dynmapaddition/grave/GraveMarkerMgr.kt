package kr.sul.dynmapaddition.grave

import kr.sul.dynmapaddition.DynmapAddition.Companion.plugin
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

object GraveMarkerMgr : Listener {
    private const val VALIDITY_TERM = 300 //s
    @EventHandler
    fun onDeath(e: PlayerDeathEvent) {
        GraveMarkerSet.createGraveMarker(e.entity, VALIDITY_TERM)
    }

    init {
        GraveMarkerSet.removeAllMarkers()
        // 정기적으로 만료된 Grave Marker 제거
        Bukkit.getScheduler().runTaskTimer(plugin, {
            GraveMarkerSet.removeExpiredMarkers()
        }, 5*20, 5*20)
    }
}