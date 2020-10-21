package de.mtorials.modem

import de.mtorials.modem.db.tables.GlobalPlayerStatistics
import org.bukkit.OfflinePlayer
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

data class Stats(
    val player: OfflinePlayer,
    val kills: Int,
    val deaths: Int,
) {
    fun getKD() : Double = kills.toDouble() / deaths.toDouble()

    override fun toString() = "K/D: $kills / $deaths = ${getKD()}"

    companion object {
        fun getStatsForPlayer(player: OfflinePlayer) : Stats? {
            var stats: Stats? = null
            transaction {
                GlobalPlayerStatistics.select { GlobalPlayerStatistics.player eq player.uniqueId }.forEach {
                    stats = Stats(player, it[GlobalPlayerStatistics.kills], it[GlobalPlayerStatistics.deaths])
                }
            }
            return stats
        }
    }
}