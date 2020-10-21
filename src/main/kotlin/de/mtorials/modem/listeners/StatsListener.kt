package de.mtorials.modem.listeners

import br.com.devsrsouza.kotlinbukkitapi.dsl.scoreboard.scoreboard
import br.com.devsrsouza.kotlinbukkitapi.extensions.event.event
import br.com.devsrsouza.kotlinbukkitapi.extensions.event.events
import br.com.devsrsouza.kotlinbukkitapi.extensions.plugin.WithPlugin
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.translateColor
import de.mtorials.modem.ModemPlugin
import de.mtorials.modem.db.tables.GlobalPlayerStatistics
import org.bukkit.event.entity.PlayerDeathEvent
import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.transactions.transactionScope
import org.jetbrains.exposed.sql.update

class StatsListener(override val plugin: ModemPlugin) : WithPlugin<ModemPlugin> {
    init {
        events {
            event<PlayerDeathEvent> {
                transaction {
                    GlobalPlayerStatistics.update({GlobalPlayerStatistics.player eq entity.uniqueId}) {
                        with(SqlExpressionBuilder) {
                            it.update(GlobalPlayerStatistics.deaths, GlobalPlayerStatistics.deaths + 1)
                        }
                    }
                }
                if (entity.killer == null) return@event
                transaction {
                    GlobalPlayerStatistics.update({GlobalPlayerStatistics.player eq entity.killer!!.uniqueId}) {
                        with(SqlExpressionBuilder) {
                            it.update(GlobalPlayerStatistics.kills, GlobalPlayerStatistics.kills + 1)
                        }
                    }
                }
            }
        }
    }
}