package de.mtorials.modem.commands

import br.com.devsrsouza.kotlinbukkitapi.dsl.command.arguments.double
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.command
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.player
import br.com.devsrsouza.kotlinbukkitapi.extensions.plugin.WithPlugin
import de.mtorials.modem.ModemPlugin

class FlyCommand(override val plugin: ModemPlugin) : WithPlugin<ModemPlugin> {

    init {
        command("fly") {
            permission = "modem.fly"
            executorPlayer {
                player.allowFlight = !player.allowFlight
            }
            command("speed") {
                executorPlayer {
                    player.flySpeed = double(0).toFloat() / 10
                }
            }
        }
    }
}