package de.mtorials.modem

import br.com.devsrsouza.kotlinbukkitapi.extensions.item.item
import de.mtorials.modem.config.Kit
import de.mtorials.modem.config.StandardKit
import org.bukkit.Material
import org.bukkit.inventory.PlayerInventory

fun PlayerInventory.standardKit(kit: StandardKit) {
    this.setArmorContents(kit.armour.map { item(it) }.toTypedArray())
    kit.items.map { item(it) }.forEach { addItem(it) }
    this.setItemInOffHand(item(kit.offHand))
}

fun PlayerInventory.equip(kit: Kit) {
    kit.items.forEach {
        this.addItem(item(it))
    }
}