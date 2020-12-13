package kr.sul.dynmapaddition

import kr.sul.dynmapaddition.grave.GraveMarkerMgr
import kr.sul.dynmapaddition.playervisibility.ShowOnlySurvivalPlayers
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

class DynmapAddition : JavaPlugin() {
    companion object {
        internal lateinit var plugin: Plugin private set
    }

    override fun onEnable() {
        plugin = this
        registerClasses()
    }
    private fun registerClasses() {
        Bukkit.getPluginManager().registerEvents(ShowOnlySurvivalPlayers, this)
        Bukkit.getPluginManager().registerEvents(GraveMarkerMgr, this)
    }
}