package de.mtorials.modem.commands

import br.com.devsrsouza.kotlinbukkitapi.dsl.command.command
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.player
import br.com.devsrsouza.kotlinbukkitapi.extensions.bungeecord.bungeecord
import br.com.devsrsouza.kotlinbukkitapi.extensions.plugin.WithPlugin
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.msg
import de.mtorials.modem.ModemPlugin

class BuildCommand(override val plugin: ModemPlugin) : WithPlugin<ModemPlugin> {

    private val buildServerName = plugin.conf.config.buildServerName

    init {
        command("skyblock") {
            executorPlayer {
                player.msg("Sending to build server")
                player.bungeecord.sendToServer(buildServerName)
            }
        }
    }
}