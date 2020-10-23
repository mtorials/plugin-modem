package de.mtorials.modem.listeners

import br.com.devsrsouza.kotlinbukkitapi.extensions.event.event
import br.com.devsrsouza.kotlinbukkitapi.extensions.event.events
import br.com.devsrsouza.kotlinbukkitapi.extensions.item.item
import br.com.devsrsouza.kotlinbukkitapi.extensions.plugin.WithPlugin
import br.com.devsrsouza.kotlinbukkitapi.extensions.server.player
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.msg
import com.destroystokyo.paper.event.player.PlayerJumpEvent
import com.sk89q.worldedit.bukkit.BukkitAdapter
import com.sk89q.worldedit.world.World
import com.sk89q.worldguard.WorldGuard
import com.sk89q.worldguard.protection.managers.RegionManager
import com.sk89q.worldguard.protection.regions.ProtectedRegion
import de.mtorials.modem.ModemPlugin
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.inventory.ItemStack
import java.util.*

class ElytraListener(override val plugin: ModemPlugin) : WithPlugin<ModemPlugin> {

    private val chestItemByPlayer: MutableMap<UUID, ItemStack?> = mutableMapOf()
    private val chestSlot = 2
    private val triggerHeight = 3
    private val elytraRegion = getElytraRegion()

    init {
        events {
            event<PlayerJumpEvent> {
                if (!elytraRegion.contains(player.location.blockX, player.location.blockY, player.location.blockZ)) return@event
                chestItemByPlayer[player.uniqueId] = player.inventory.armorContents[chestSlot]
                player.inventory.setArmorContents(arrayOf(
                    player.inventory.armorContents[0],
                    player.inventory.armorContents[1],
                    item(Material.ELYTRA),
                    player.inventory.armorContents[3]
                ))
                player.msg("You got an Elytra: FLY BABY FLY!")
            }
            event<PlayerMoveEvent> {
                if (!(player as Entity).isOnGround) return@event
                if (!chestItemByPlayer.containsKey(player.uniqueId)) return@event
                player.inventory.setArmorContents(arrayOf(
                    player.inventory.armorContents[0],
                    player.inventory.armorContents[1],
                    chestItemByPlayer[player.uniqueId],
                    player.inventory.armorContents[3]
                ))
                chestItemByPlayer.remove(player.uniqueId)
                player.msg("No elytra anymore...")
            }
            event<InventoryClickEvent> {
                if (this.currentItem?.type != Material.ELYTRA) return@event
                result = Event.Result.DENY
            }
        }
    }

    private fun getElytraRegion() : ProtectedRegion {
        val container = WorldGuard.getInstance().platform.regionContainer
        val regions: RegionManager = container.get(BukkitAdapter.adapt(Bukkit.getWorld(plugin.conf.config.lobbyWorldName))) ?: throw RuntimeException("No Region Manager gor world")
        return regions.getRegion(plugin.conf.config.elytraRegionName) ?: throw RuntimeException("Region not found!")
    }

    private fun getEntityGroundDistance(e: Player): Int {
        val loc: Location = e.location.clone()
        loc.add(e.location.direction.normalize())
        val y: Int = loc.blockY
        var distance = 0
        for (i in y downTo 0) {
            loc.y = i.toDouble()
            if (loc.block.type.isSolid) break
            distance++
        }
        return distance
    }
}