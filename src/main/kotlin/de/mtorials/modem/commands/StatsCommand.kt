package de.mtorials.modem.commands

import br.com.devsrsouza.kotlinbukkitapi.dsl.command.arguments.offlinePlayer
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.command
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.fail
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.player
import br.com.devsrsouza.kotlinbukkitapi.extensions.plugin.WithPlugin
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.color
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.msg
import de.mtorials.modem.ModemPlugin
import de.mtorials.modem.Stats
import org.bukkit.ChatColor

class StatsCommand(override val plugin: ModemPlugin) : WithPlugin<ModemPlugin> {

    init {
        command("stats") {
            description = "See the stats of other users"
            usage = "stats <palyer>"
            executorPlayer {
                val statsPlayer = offlinePlayer(0)
                val stats: Stats = Stats.getStatsForPlayer(statsPlayer) ?:
                    fail("No Stats available".color(ChatColor.RED))
                player.msg("Stats of ${statsPlayer.name} are: \n-> $stats".color(ChatColor.YELLOW))
            }
        }
    }
}