package de.mtorials.modem.listeners

import br.com.devsrsouza.kotlinbukkitapi.extensions.event.event
import br.com.devsrsouza.kotlinbukkitapi.extensions.event.events
import br.com.devsrsouza.kotlinbukkitapi.extensions.plugin.WithPlugin
import de.mtorials.modem.ModemPlugin
import org.bukkit.event.player.PlayerJoinEvent

class JoinListener(override val plugin: ModemPlugin) : WithPlugin<ModemPlugin> {
    init {
        events {
            event<PlayerJoinEvent> {
                player.teleport(player.world.spawnLocation)
            }
        }
    }
}