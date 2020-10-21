package de.mtorials.modem.commands

import br.com.devsrsouza.kotlinbukkitapi.dsl.command.command
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.player
import br.com.devsrsouza.kotlinbukkitapi.extensions.bukkit.server
import br.com.devsrsouza.kotlinbukkitapi.extensions.event.event
import br.com.devsrsouza.kotlinbukkitapi.extensions.event.events
import br.com.devsrsouza.kotlinbukkitapi.extensions.plugin.WithPlugin
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.color
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.msg
import de.mtorials.modem.ModemPlugin
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageEvent
import java.util.*

class SpawnCommand(override val plugin: ModemPlugin) : WithPlugin<ModemPlugin> {

    private val playersLastDamage = mutableMapOf<UUID, Int>()
    private val delayTime: Int = plugin.conf.config.delayTime

    init {
        command("spawn") {
            permission = "modem.spawn"
            executorPlayer {
                val ticksSince: Int? =
                    if (playersLastDamage.containsKey(player.uniqueId)) server.currentTick - playersLastDamage[player.uniqueId]!!
                    else null

                if (ticksSince == null || ticksSince > delayTime || player.hasPermission("modem.nocooldown")) {
                    player.msg("Teleport...".color(ChatColor.GREEN))
                    player.teleport(player.world.spawnLocation)
                } else {
                    player.msg("You can not warp when in combat. Wait for ${delayTime - ticksSince} ticks.".color(ChatColor.YELLOW))
                }
            }
        }
        events {
            event<EntityDamageEvent> {
                if (entity is Player) {
                    if (entity.lastDamageCause == null) {
                        return@event
                    }
                    if (entity.lastDamageCause!!.cause == EntityDamageEvent.DamageCause.ENTITY_ATTACK
                        || entity.lastDamageCause!!.cause == EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK) {

                        playersLastDamage[entity.uniqueId] = server.currentTick
                    }
                }
            }
        }
    }
}