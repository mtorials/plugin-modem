package de.mtorials.modem.commands

import br.com.devsrsouza.kotlinbukkitapi.dsl.command.arguments.string
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.command
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.fail
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.player
import br.com.devsrsouza.kotlinbukkitapi.extensions.item.item
import br.com.devsrsouza.kotlinbukkitapi.extensions.plugin.WithPlugin
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.color
import de.mtorials.modem.ModemPlugin
import de.mtorials.modem.getBalance
import de.mtorials.modem.withdraw
import org.bukkit.ChatColor
import org.bukkit.Material

class KitCommand(override val plugin: ModemPlugin) : WithPlugin<ModemPlugin> {

    init {
        command("kit") {
            permission = "modem.kit"
            executorPlayer {
                when (string(0)) {
                    "wood" -> {
                        if (player.getBalance() < 10.0) fail("This kit is too expensive!".color(ChatColor.RED))
                        player.inventory.addItem(
                            item(Material.LEATHER_CHESTPLATE),
                            item(Material.LEATHER_BOOTS),
                            item(Material.LEATHER_HELMET),
                            item(Material.LEATHER_LEGGINGS),
                            item(Material.WOODEN_SWORD)
                        )
                        player.withdraw(10.0)
                    }
                    "iron" -> {
                        if (player.getBalance() < 25.0) fail("This kit is too expensive!".color(ChatColor.RED))
                        player.inventory.addItem(
                            item(Material.IRON_CHESTPLATE),
                            item(Material.IRON_LEGGINGS),
                            item(Material.IRON_SWORD)
                        )
                        player.withdraw(25.0);
                    }
                }
            }
        }
    }
}