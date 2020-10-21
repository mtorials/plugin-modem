package de.mtorials.modem.config

import br.com.devsrsouza.kotlinbukkitapi.serialization.ChangeColor
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.bukkit.Material

@Serializable
data class Config(
    @ChangeColor
    val noPermissionMessage: String = "&cSorry, you do not have the permission to use this command!",
    val coins: Coins = Coins(),
    //val kits: List<Kit> = listOf()
    @Contextual
    val kits: List<Kit> = listOf(
        Kit(Material.STONE, "Stone Kit",10.0, listOf(Material.STONE_SWORD, Material.LEATHER_LEGGINGS, Material.LEATHER_CHESTPLATE)),
        Kit(Material.IRON_NUGGET, "Iron Kit",20.0, listOf(Material.IRON_SWORD, Material.LEATHER_LEGGINGS, Material.LEATHER_CHESTPLATE)),
        Kit(Material.DIAMOND, "Diamond Kit",100.0, listOf(Material.DIAMOND_SWORD, Material.LEATHER_LEGGINGS, Material.LEATHER_CHESTPLATE)),
        Kit(Material.NETHERITE_INGOT, "Netherrite Kit",200.0, listOf(Material.NETHERITE_SWORD, Material.LEATHER_LEGGINGS, Material.LEATHER_CHESTPLATE))
    )
)

@Serializable
data class Coins(
    val coinsPerKill: Double = 10.0
)

@Serializable
data class Kit(
    val display: Material = Material.STONE,
    val name: String = "Default Kit",
    val price: Double = 0.0,
    @Contextual
    val items: List<Material> = listOf()
)