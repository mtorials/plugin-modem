package de.mtorials.modem.listeners

import br.com.devsrsouza.kotlinbukkitapi.extensions.event.event
import br.com.devsrsouza.kotlinbukkitapi.extensions.event.events
import br.com.devsrsouza.kotlinbukkitapi.extensions.plugin.WithPlugin
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.color
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.msg
import de.mtorials.modem.ModemPlugin
import org.bukkit.ChatColor
import org.bukkit.event.entity.PlayerDeathEvent

const val EXPECTED_MAX_HEALTH = 20.0

class KillListener(override val plugin: ModemPlugin) : WithPlugin<ModemPlugin> {
    init {
        events {
            event<PlayerDeathEvent> {
                entity.killer?.health = EXPECTED_MAX_HEALTH
                entity.killer?.msg("You killed ${entity.name}. MAX HEALTH".color(ChatColor.GREEN))
            }
        }
    }
}