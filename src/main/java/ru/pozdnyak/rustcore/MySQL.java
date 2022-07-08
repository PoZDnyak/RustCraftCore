package ru.pozdnyak.rustcore;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import java.sql.*;

public class MySQL {
    private Connection conn;
    private Statement statmt;
    private PreparedStatement preparedStatement = null;

    public MySQL(String host, String databaseName, String username, String password) throws Exception {
        try {
            System.out.println("jdbc:mysql://" + host + ":3306/" + databaseName + "?useUnicode=true&characterEncoding=utf8&autoReconnect=true"+ username + password);
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + databaseName + "?useUnicode=true&characterEncoding=utf8&autoReconnect=true", username, password);
            statmt = conn.createStatement();
            statmt.executeUpdate("CREATE TABLE IF NOT EXISTS barrels (`ID` INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, `x` INT, `y` INT, `z` INT,`color` TINYINT);");
            statmt.executeUpdate("CREATE TABLE IF NOT EXISTS resources (`ID` INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL, `x` INT, `y` INT, `z` INT);");
            statmt.executeUpdate("CREATE TABLE IF NOT EXISTS stats (`nick` TEXT(50), `food` INT, `water` INT, `radiation` INT, `UUID` TEXT(36),PRIMARY KEY (`nick`(50)));");
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public int refreshBarrels(){
        try {
            int count = 0;
            preparedStatement = conn.prepareStatement("SELECT * FROM `barrels`;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int x = resultSet.getInt(2);
                int y = resultSet.getInt(3);
                int z = resultSet.getInt(4);
                int color = resultSet.getInt(5);
                Location location = new Location(Bukkit.getWorld("world"),x,y,z);
                if(color==0){
                    location.getBlock().setType(Material.LAPIS_BLOCK);
                }else if(color == 1){
                    location.getBlock().setType(Material.REDSTONE_BLOCK);
                }else continue;
                count++;
            }

            return count;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    public void addBarrel(int x,int y,int z, int color){
        try {
            preparedStatement = conn.prepareStatement("INSERT INTO barrels (x,y,z,color) VALUES (?,?,?,?);");
            preparedStatement.setInt(1,x);
            preparedStatement.setInt(2, y);
            preparedStatement.setInt(3,z);
            preparedStatement.setInt(4,color);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addSpawnResources(int x,int y,int z){
        try {
            preparedStatement = conn.prepareStatement("INSERT INTO resources (x,y,z) VALUES (?,?,?);");
            preparedStatement.setInt(1,x);
            preparedStatement.setInt(2, y);
            preparedStatement.setInt(3,z);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createNewStats(String nick, int food, int water, int radiation, String UUID){
        try {
            preparedStatement = conn.prepareStatement("INSERT INTO stats (nick,food,water,radiation,UUID) VALUES (?,?,?,?,?);");
            preparedStatement.setString(1,nick);
            preparedStatement.setInt(2,food);
            preparedStatement.setInt(3,water);
            preparedStatement.setInt(4,radiation);
            preparedStatement.setString(5,UUID);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public int[] getStats(String nickname){
        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM stats WHERE nick = ?;");
            preparedStatement.setString(1,nickname);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int[] stats = new int[3];
                stats[0] = Integer.parseInt(resultSet.getString("food"));
                stats[1] = Integer.parseInt(resultSet.getString("water"));
                stats[2] = Integer.parseInt(resultSet.getString("radiation"));
                return stats;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public void setStats(String nick,int food, int water, int radiation){
        try {
            preparedStatement = conn.prepareStatement("UPDATE stats SET water = '"+water+"', food = '"+food+"', radiation = '"+radiation+"' WHERE nick = '"+nick+"';");
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String getUUID(String nickname){
        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM stats WHERE nick = ?;");
            preparedStatement.setString(1,nickname);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                String UUID;
                UUID = resultSet.getString("UUID");
                return UUID;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



    public void closeConnection() throws SQLException {
        conn.close();
        statmt.close();
    }




}
