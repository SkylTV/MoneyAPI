package de.skyl.coins.api;
// Coded By SkylTV //
// Copyright SkylTV //

import de.skyl.coins.mysql.MySQL;
import org.bukkit.OfflinePlayer;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MoneyService implements MoneyAPI{

    private MySQL mySQL;

        public MoneyService(){
            mySQL = new MySQL();
        }

        public void setMoney(OfflinePlayer player, int amount){
            try {
                PreparedStatement preparedStatement  = MySQL.getStatement("UPDATE money SET Money=? WHERE UUID=?");
                preparedStatement.setInt(1, amount);
                preparedStatement.setString(2, player.getUniqueId().toString());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }catch (SQLException exception){
                exception.printStackTrace();
            }
        }

        public void addMoney(OfflinePlayer player, int amount){
            try {
                PreparedStatement preparedStatement  = MySQL.getStatement("UPDATE money SET Money=? WHERE UUID=?");
                preparedStatement.setInt(1, getMoney(player) + amount);
                preparedStatement.setString(2, player.getUniqueId().toString());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }catch (SQLException exception){
                exception.printStackTrace();
            }
        }

        public void removeMoney(OfflinePlayer player, int amount){
            try {
                PreparedStatement preparedStatement  = MySQL.getStatement("UPDATE money SET Money=? WHERE UUID=?");
                preparedStatement.setInt(1, getMoney(player) - amount);
                preparedStatement.setString(2, player.getUniqueId().toString());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }catch (SQLException exception){
                exception.printStackTrace();
            }
        }

        public int getMoney(OfflinePlayer player){
            try {
                PreparedStatement preparedStatement  = MySQL.getStatement("SELECT * FROM money WHERE UUID=?");
                preparedStatement.setString(1, player.getUniqueId().toString());
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                int amount = resultSet.getInt("Money");
                preparedStatement.close();
                resultSet.close();
                return amount;
            }catch (SQLException exception){
                exception.printStackTrace();
            }
            return -1;
        }

}
