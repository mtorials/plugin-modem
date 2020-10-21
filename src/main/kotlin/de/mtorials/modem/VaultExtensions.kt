package de.mtorials.modem

import br.com.devsrsouza.kotlinbukkitapi.extensions.server.offlinePlayer
import br.com.devsrsouza.kotlinbukkitapi.plugins.vault.economy
import org.bukkit.entity.Player

fun Player.getBalance() : Double {
    if (economy == null) throw RuntimeException("Economy is null!")
    if (!economy!!.hasAccount(offlinePlayer(this.uniqueId))) {
        economy!!.createPlayerAccount(offlinePlayer(this.uniqueId))
    }
    return economy!!.getBalance(offlinePlayer(this.uniqueId))
}

fun Player.withdraw(amount: Double) {
    if (economy == null) throw RuntimeException("Economy is null!")
    if (!economy!!.hasAccount(offlinePlayer(this.uniqueId))) {
        economy!!.createPlayerAccount(offlinePlayer(this.uniqueId))
    }
    economy!!.withdrawPlayer(offlinePlayer(this.uniqueId), amount)
}

fun Player.deposite(amount: Double) {
    if (economy == null) throw RuntimeException("Economy is null!")
    if (!economy!!.hasAccount(offlinePlayer(this.uniqueId))) {
        economy!!.createPlayerAccount(offlinePlayer(this.uniqueId))
    }
    economy!!.depositPlayer(offlinePlayer(this.uniqueId), amount)
}