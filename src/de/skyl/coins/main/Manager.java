package de.skyl.coins.main;
// Coded By SkylTV //
// Copyright SkylTV //

import de.skyl.coins.listeners.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class Manager {

    private Main main;
    private PluginManager pluginManager;

    public Manager(){
        main = Core.getCore().getMain();
        pluginManager = Bukkit.getPluginManager();
    }

    public void registerListeners(){
        pluginManager.registerEvents(new JoinListener(), main);
    }

    public void registerCommands(){
    }

}
