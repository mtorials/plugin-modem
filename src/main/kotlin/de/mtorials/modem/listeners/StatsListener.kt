package de.mtorials.modem.listeners

import br.com.devsrsouza.kotlinbukkitapi.extensions.event.event
import br.com.devsrsouza.kotlinbukkitapi.extensions.event.events
import br.com.devsrsouza.kotlinbukkitapi.extensions.plugin.WithPlugin
import br.com.devsrsouza.kotlinbukkitapi.extensions.plugin.info
import de.mtorials.modem.ModemPlugin
import de.mtorials.modem.db.tables.GlobalPlayerStatistics
import org.bukkit.event.entity.PlayerDeathEvent
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class StatsListener(override val plugin: ModemPlugin) : WithPlugin<ModemPlugin> {
    init {
        events {
            event<PlayerDeathEvent> {

                transaction {
                    val empty = GlobalPlayerStatistics.select {
                        GlobalPlayerStatistics.player eq entity.uniqueId
                    }.empty()
                    plugin.info("New Player detected, adding stats")
                    if (empty) {
                        GlobalPlayerStatistics.insert {
                            it[player] = entity.uniqueId
                            it[deaths] = 0
                            it[kills] = 0
                        }
                    }
                }

                transaction {
                    addLogger(StdOutSqlLogger)
                    GlobalPlayerStatistics.update({GlobalPlayerStatistics.player eq entity.uniqueId}) {
                        with(SqlExpressionBuilder) {
                            it.update(GlobalPlayerStatistics.deaths, GlobalPlayerStatistics.deaths + 1)
                        }
                    }
                }
                if (entity.killer == null) return@event

                transaction {
                    val empty = GlobalPlayerStatistics.select {
                        GlobalPlayerStatistics.player eq entity.killer!!.uniqueId
                    }.empty()
                    plugin.info("New Player detected, adding stats")
                    if (empty) {
                        GlobalPlayerStatistics.insert {
                            it[player] = entity.killer!!.uniqueId
                            it[deaths] = 0
                            it[kills] = 0
                        }
                    }
                }

                transaction {
                    addLogger(StdOutSqlLogger)
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