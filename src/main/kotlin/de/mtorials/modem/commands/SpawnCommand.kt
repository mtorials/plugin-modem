package de.mtorials.modem.commands

import br.com.devsrsouza.kotlinbukkitapi.dsl.command.command
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.player
import br.com.devsrsouza.kotlinbukkitapi.extensions.bukkit.server
import br.com.devsrsouza.kotlinbukkitapi.extensions.plugin.WithPlugin
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.color
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.msg
import de.mtorials.modem.ModemPlugin
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.entity.Player

// in ticks
const val DELAY_TIME = 60L

class SpawnCommand(override val plugin: ModemPlugin) : WithPlugin<ModemPlugin> {
    init {
        command("spawn") {
            permission = "modem.spawn"
            executorPlayer {
                player.msg("Teleporting in 3s...".color(ChatColor.GREEN))
                player.server.scheduler.scheduleSyncDelayedTask(plugin, Teleporter(player.world.spawnLocation, player), DELAY_TIME)
            }
        }
    }
}

class Teleporter(
    private val location: Location,
    private val player: Player
) : Runnable {
    override fun run() {
        player.teleport(location)
    }
}