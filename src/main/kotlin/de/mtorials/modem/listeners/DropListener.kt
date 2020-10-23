package de.mtorials.modem.listeners

import br.com.devsrsouza.kotlinbukkitapi.extensions.event.event
import br.com.devsrsouza.kotlinbukkitapi.extensions.event.events
import br.com.devsrsouza.kotlinbukkitapi.extensions.item.item
import br.com.devsrsouza.kotlinbukkitapi.extensions.plugin.WithPlugin
import de.mtorials.modem.ModemPlugin
import org.bukkit.Material
import org.bukkit.event.entity.ItemSpawnEvent

class DropListener(override val plugin: ModemPlugin) : WithPlugin<ModemPlugin> {
    init {
        events {
            event<ItemSpawnEvent> {
                val type = entity.itemStack.type
                val isStandardKit = this@DropListener.plugin.conf.config.standardKit
                    .getAllItems().map { item(it).type }.contains(type)
                if (isStandardKit) entity.remove()
            }
        }
    }
}