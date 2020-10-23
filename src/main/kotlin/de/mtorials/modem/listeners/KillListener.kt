package de.mtorials.modem.listeners

import br.com.devsrsouza.kotlinbukkitapi.extensions.event.event
import br.com.devsrsouza.kotlinbukkitapi.extensions.event.events
import br.com.devsrsouza.kotlinbukkitapi.extensions.plugin.WithPlugin
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.color
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.msg
import de.mtorials.modem.ModemPlugin
import de.mtorials.modem.db.tables.GlobalPlayerStatistics
import de.mtorials.modem.deposite
import de.mtorials.modem.standardKit
import org.bukkit.ChatColor
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerRespawnEvent
import org.jetbrains.exposed.sql.transactions.transaction
import org.spigotmc.event.player.PlayerSpawnLocationEvent

const val EXPECTED_MAX_HEALTH = 20.0

class KillListener(override val plugin: ModemPlugin) : WithPlugin<ModemPlugin> {
    init {

        val coinsPerKill = plugin.conf.config.coins.coinsPerKill

        events {
            // TODO On fall in void also kill
            event<PlayerDeathEvent> {
                entity.killer?.health = EXPECTED_MAX_HEALTH
                entity.killer?.msg("You killed ${entity.name}. MAX HEALTH".color(ChatColor.GREEN))
                // TODO say how much health point the killer had
                entity.killer?.msg(("+ $coinsPerKill").color(ChatColor.GREEN))
                entity.killer?.deposite(coinsPerKill)
            }
            event<PlayerRespawnEvent> {
                player.msg("Equipped with standard kit!".color(ChatColor.BLUE))
                player.inventory.standardKit(this@KillListener.plugin.conf.config.standardKit)
            }
        }
    }
}