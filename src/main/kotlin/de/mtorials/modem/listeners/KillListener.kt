package de.mtorials.modem.listeners

import br.com.devsrsouza.kotlinbukkitapi.extensions.event.event
import br.com.devsrsouza.kotlinbukkitapi.extensions.event.events
import br.com.devsrsouza.kotlinbukkitapi.extensions.plugin.WithPlugin
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.color
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.msg
import de.mtorials.modem.ModemPlugin
import de.mtorials.modem.deposite
import de.mtorials.modem.standardKit
import org.bukkit.ChatColor
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerRespawnEvent
import org.spigotmc.event.player.PlayerSpawnLocationEvent

const val EXPECTED_MAX_HEALTH = 20.0
const val COINS_PER_KILL = 10.0

class KillListener(override val plugin: ModemPlugin) : WithPlugin<ModemPlugin> {
    init {
        events {
            event<PlayerDeathEvent> {
                entity.killer?.health = EXPECTED_MAX_HEALTH
                entity.killer?.msg("You killed ${entity.name}. MAX HEALTH".color(ChatColor.GREEN))
                entity.killer?.msg("+ $COINS_PER_KILL coins".color(ChatColor.GREEN))
                entity.killer?.deposite(COINS_PER_KILL)
            }
            event<PlayerRespawnEvent> {
                player.msg("Equipped with standard kit!".color(ChatColor.BLUE))
                player.inventory.standardKit()
            }
        }
    }
}