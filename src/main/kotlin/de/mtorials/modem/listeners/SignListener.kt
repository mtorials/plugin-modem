package de.mtorials.modem.listeners

import br.com.devsrsouza.kotlinbukkitapi.extensions.event.event
import br.com.devsrsouza.kotlinbukkitapi.extensions.event.events
import br.com.devsrsouza.kotlinbukkitapi.extensions.plugin.WithPlugin
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.color
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.msg
import de.mtorials.modem.ModemPlugin
import de.mtorials.modem.deposite
import org.bukkit.ChatColor
import org.bukkit.block.Sign
import org.bukkit.block.data.type.WallSign
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class SignListener(override val plugin: ModemPlugin) : WithPlugin<ModemPlugin> {
    init {
        events {
            event<PlayerInteractEvent> {
                if (clickedBlock == null) return@event
                if (action != Action.RIGHT_CLICK_BLOCK) return@event

                // Check if Block is sign
                if (!(clickedBlock!!.blockData is org.bukkit.block.data.type.Sign || clickedBlock!!.blockData is WallSign)) return@event

                // Cast blickdata to sign
                val signBlock: Sign = clickedBlock!!.state as Sign

                if (signBlock.lines[0] == "[modem]") {
                    player.deposite(signBlock.lines[1].toDouble())
                    player.msg("You got ${signBlock.lines[1].toDouble()} coins.".color(ChatColor.GREEN))
                }
            }
        }
    }
}