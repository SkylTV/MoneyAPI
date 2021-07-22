package de.skyl.coins.api;

import org.bukkit.OfflinePlayer;

public interface MoneyAPI {

    int getMoney(OfflinePlayer player);
    void setMoney(OfflinePlayer player, int amount);
    void addMoney(OfflinePlayer player, int amount);
    void removeMoney(OfflinePlayer player, int amount);
}
