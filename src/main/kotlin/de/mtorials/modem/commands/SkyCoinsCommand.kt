package de.mtorials.modem.commands

import br.com.devsrsouza.kotlinbukkitapi.dsl.command.arguments.double
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.arguments.int
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.command
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.player
import br.com.devsrsouza.kotlinbukkitapi.extensions.plugin.WithPlugin
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.color
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.msg
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.plus
import br.com.devsrsouza.kotlinbukkitapi.plugins.vault.permission
import de.mtorials.modem.ModemPlugin
import de.mtorials.modem.deposite
import de.mtorials.modem.getBalance
import de.mtorials.modem.withdraw
import org.bukkit.ChatColor

class SkyCoinsCommand(override val plugin: ModemPlugin) : WithPlugin<ModemPlugin> {

    init {
        command("skycoins") {
            executorPlayer {
                player.msg("You have ".color(ChatColor.GREEN) + player.getBalance().toString().color(ChatColor.GOLD) + " skycoins!")
            }
            command("deposite") {
                permission = "modem.deposit"
                executorPlayer {
                    player.deposite(double(0))
                    player.msg("Deposited!")
                }
            }
            command("withdraw") {
                permission = "modem.deposit"
                executorPlayer {
                    player.withdraw(double(0))
                    player.msg("Withdraw!")
                }
            }
        }
    }
}