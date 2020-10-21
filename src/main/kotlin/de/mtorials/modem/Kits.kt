package de.mtorials.modem

import br.com.devsrsouza.kotlinbukkitapi.extensions.item.item
import org.bukkit.Material
import org.bukkit.inventory.PlayerInventory

fun PlayerInventory.standardKit() {
    this.setArmorContents(arrayOf(null, item(Material.LEATHER_LEGGINGS), item(Material.LEATHER_CHESTPLATE), null))
    this.addItem(item(Material.STONE_SWORD))
}