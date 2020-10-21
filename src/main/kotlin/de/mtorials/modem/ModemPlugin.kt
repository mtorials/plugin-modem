package de.mtorials.modem

import br.com.devsrsouza.kotlinbukkitapi.architecture.KotlinPlugin
import br.com.devsrsouza.kotlinbukkitapi.dsl.command.command
import br.com.devsrsouza.kotlinbukkitapi.exposed.DatabaseTypeConfig
import br.com.devsrsouza.kotlinbukkitapi.exposed.databaseTypeFrom
import br.com.devsrsouza.kotlinbukkitapi.extensions.text.msg
import br.com.devsrsouza.kotlinbukkitapi.serialization.architecture.config
import com.zaxxer.hikari.HikariDataSource
import de.mtorials.modem.commands.KitCommand
import de.mtorials.modem.commands.SkyCoinsCommand
import de.mtorials.modem.commands.SpawnCommand
import de.mtorials.modem.config.Config
import de.mtorials.modem.db.tables.GlobalPlayerStatistics
import de.mtorials.modem.listeners.KillListener
import de.mtorials.modem.listeners.SignListener
import de.mtorials.modem.listeners.StatsListener
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File

class ModemPlugin : KotlinPlugin() {

    val conf = config(
        "config.yml",
        Config(),
        Config.serializer()
    )

    private val dbConfig = DatabaseTypeConfig(
        type = "MySQL",
        hostname = "localhost",
        port = 3306,
        database = "modem",
        user = "mcplug",
        password = "mcplug"
    )

    private val databaseType = databaseTypeFrom(dataFolder = File("modem.dat"), config = dbConfig)
    private val dataSource: HikariDataSource = databaseType.dataSource()

    val database = Database.connect(dataSource)

    init {
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(GlobalPlayerStatistics)
        }
    }

    override fun onPluginEnable() {
        this.command("ping") {
            permission = "modem.ping"
            executor {
                sender.msg("Pong!")
            }
        }
        SpawnCommand(this)
        KillListener(this)
        KitCommand(this)
        SkyCoinsCommand(this)
        SignListener(this)
        StatsListener(this)
    }
    
    override fun onPluginDisable() {
        println("Disable Modem")
    }
}