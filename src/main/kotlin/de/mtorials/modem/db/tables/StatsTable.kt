package de.mtorials.modem.db.tables

import org.jetbrains.exposed.sql.Table

object GlobalPlayerStatistics : Table() {
    val player = uuid("player").uniqueIndex()
    val kills = integer("kills")
    val deaths = integer("deaths")
}