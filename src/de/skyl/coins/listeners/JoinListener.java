package de.skyl.coins.listeners;
// Coded By SkylTV //
// Copyright SkylTV //

import de.skyl.coins.main.Core;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        OfflinePlayer player = event.getPlayer();
        if(!Core.getCore().getMain().getMySQL().isPlayerRegistered(player)){
            Core.getCore().getMain().getMySQL().registerPlayer(player);
        }
    }


}
