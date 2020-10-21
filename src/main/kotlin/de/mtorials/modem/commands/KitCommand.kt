package de.mtorials.modem.commands

import br.com.devsrsouza.kotlinbukkitapi.dsl.command.Executor
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.command
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.fail
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.player
import br.com.devsrsouza.kotlinbukkitapi.dsl.menu.menu
import br.com.devsrsouza.kotlinbukkitapi.dsl.menu.slot
import br.com.devsrsouza.kotlinbukkitapi.extensions.item.displayName
import br.com.devsrsouza.kotlinbukkitapi.extensions.item.item
import br.com.devsrsouza.kotlinbukkitapi.extensions.plugin.WithPlugin
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.color
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.msg
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.translateColor
import de.mtorials.modem.ModemPlugin
import de.mtorials.modem.equip
import de.mtorials.modem.getBalance
import de.mtorials.modem.withdraw
import org.bukkit.ChatColor


class KitCommand(override val plugin: ModemPlugin) : WithPlugin<ModemPlugin> {

    init {
        val kitMenu = menu("Kits", 1, true) {
            this@KitCommand.plugin.conf.config.kits.forEachIndexed { index, kit ->
                slot(1, 1+index, item(kit.display).displayName(kit.name + " &c${kit.price}".translateColor())) {
                    onClick {
                        if (player.getBalance() < kit.price) {
                            player.msg("This kit is too expensive!".color(ChatColor.RED))
                            close()
                        } else {
                            player.inventory.equip(kit)
                            player.withdraw(kit.price)
                        }
                    }
                }
            }
        }
        command("kit") {
            permission = "modem.kit"
            executorPlayer {
                kitMenu.openToPlayer(player)
            }
        }
    }
}