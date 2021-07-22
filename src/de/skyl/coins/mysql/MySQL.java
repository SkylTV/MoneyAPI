package de.skyl.coins.mysql;
// Coded By SkylTV //
// Copyright SkylTV //

import de.skyl.coins.main.Core;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.sql.*;

public class MySQL {
    public static Connection con;
    public static String host ;
    public  static int port ;
    public static  String database ;
    public static String username;
    public static String password ;



    public void connect() {
        if(!isConnected()) {
            try {
                File file = new File(Core.getCore().getMain().getDataFolder() + "/mysql.yml");
                FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
                host = configuration.getString("host");
                port =configuration.getInt("port");
                database = configuration.getString("database");
                username = configuration.getString("username");
                password = configuration.getString("password");
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":"+port+"/"+database+"?useSSL=false", username, password);
                System.out.println("[MoneyAPI] Du wurdest erflogreich mit der Datenbank verbunden");
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }

    public static PreparedStatement getStatement(String sql) {
        if(isConnected()) {
            PreparedStatement ps;
            try {
                File file = new File(Core.getCore().getMain().getDataFolder() + "/mysql.yml");
                FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
                host = configuration.getString("host");
                port =configuration.getInt("port");
                database = configuration.getString("database");
                username = configuration.getString("username");
                password = configuration.getString("password");
                ps = con.prepareStatement(sql);
                return ps;
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public  void disconnect() {
        if(isConnected()) {
            try {
                con.close();
                System.out.println("[MoneyAPI] Du wurdest erflogreich mit der Datenbank getrennt");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


    }

    public static boolean isConnected() {
        return (con != null);
    }

    public void createTables(){
        try{
            PreparedStatement preparedStatement = getStatement("CREATE TABLE IF NOT EXISTS money (Spieler VARCHAR(100), UUID VARCHAR(100), Money INT(100));");
            preparedStatement.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public void registerPlayer(OfflinePlayer player){
        try{
            File file = new File(Core.getCore().getMain().getDataFolder() + "/config.yml");
            FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            PreparedStatement preparedStatement = getStatement("INSERT INTO money(Spieler, UUID, Money) VALUES (?,?,?)");
            preparedStatement.setString(1, player.getName());
            preparedStatement.setString(2, player.getUniqueId().toString());
            preparedStatement.setInt(3, configuration.getInt("startmoney"));
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    public boolean isPlayerRegistered(OfflinePlayer player){
        try{
            PreparedStatement preparedStatement = getStatement("SELECT * FROM money WHERE UUID=?");
            preparedStatement.setString(1, player.getUniqueId().toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return true;
            }
            resultSet.close();
            preparedStatement.close();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return false;
    }
}
