package de.skyl.coins.main;
// Coded By SkylTV //
// Copyright SkylTV //

import de.skyl.coins.mysql.MySQL;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {

    private Main instance;
    private File file;
    private MySQL mySQL;

    @Override
    public void onLoad() {
        instance = this;
        createFile();
    }

    @Override
    public void onDisable() {
        if(mySQL.isConnected()){
            mySQL.disconnect();
        }
    }

    @Override
    public void onEnable() {
        Core.setCore(new Core(this));
        Core.getCore().enableCore();
        mySQL = new MySQL();
        if(!mySQL.isConnected()){
            mySQL.connect();
            mySQL.createTables();
        }
    }

    public void createFile(){
        try {
            if(!getDataFolder().exists()){
                getDataFolder().mkdir();
            }
            file = new File(getDataFolder() + "/mysql.yml");
            if(!file.exists()){
                file.createNewFile();
                FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
                configuration.set("host", "localhost");
                configuration.set("port", 3306);
                configuration.set("username", "test");
                configuration.set("password", "test");
                configuration.set("database", "test");
                configuration.save(file);
            }
            File config = new File(getDataFolder() + "/config.yml");
            if(!config.exists()){
                config.createNewFile();
                FileConfiguration configuration = YamlConfiguration.loadConfiguration(config);
                configuration.set("startmoney", 100);
                configuration.save(config);
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public MySQL getMySQL() {
        return mySQL;
    }
}
