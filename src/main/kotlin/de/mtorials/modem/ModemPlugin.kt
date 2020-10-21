package de.mtorials.modem

import br.com.devsrsouza.kotlinbukkitapi.architecture.KotlinPlugin
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.command
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.msg
import de.mtorials.modem.commands.KitCommand
import de.mtorials.modem.commands.SkyCoinsCommand
import de.mtorials.modem.commands.SpawnCommand
import de.mtorials.modem.listeners.KillListener

class ModemPlugin : KotlinPlugin() {

    override fun onPluginEnable() {
        this.command("ping") {
            permission = "modem.ping"
            executor {
                sender.msg("Pong!")
            }
        }
        SpawnCommand(this)
        KillListener(this)
        KitCommand(this)
        SkyCoinsCommand(this)
    }
    
    override fun onPluginDisable() {
        println("Disable Modem")
    }
}