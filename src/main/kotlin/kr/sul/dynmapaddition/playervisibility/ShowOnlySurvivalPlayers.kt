package kr.sul.dynmapaddition.playervisibility

import org.bukkit.GameMode
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerGameModeChangeEvent
import org.dynmap.bukkit.DynmapPlugin

object ShowOnlySurvivalPlayers : Listener {
    @EventHandler
    fun onGameModeChanged(e: PlayerGameModeChangeEvent) {
        val p = e.player
        if (e.newGameMode == GameMode.SURVIVAL) {
            // 지도에 보이기
            DynmapPlugin.plugin.setPlayerVisiblity(p, true)
        } else {
            // 지도서 숨기기
            DynmapPlugin.plugin.setPlayerVisiblity(p, false)
        }
    }
}