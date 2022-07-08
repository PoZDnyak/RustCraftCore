package ru.pozdnyak.rustcore.PlayerStars;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import ru.pozdnyak.rustcore.Main;
import ru.pozdnyak.rustcore.MySQL;

import java.util.HashMap;

public class PlayerStats implements Listener {
    private final Main plugin;
    private MySQL mySQL;


    public PlayerStats(Main plugin) {
        this.plugin = plugin;
        try {
            mySQL = new MySQL(plugin.getConfig().getString("host"),plugin.getConfig().getString("database"),plugin.getConfig().getString("user"),plugin.getConfig().getString("password"));
        }
        catch(Exception e) {
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(plugin);
        }
    }

    public static HashMap<Player,Integer> water = new HashMap<Player,Integer>();
    public static HashMap<Player,Integer> food = new HashMap<Player,Integer>();
    public static HashMap<Player,Integer> radiation = new HashMap<Player,Integer>();
    public static HashMap<Player,Boolean> remove = new HashMap<Player,Boolean>();


    @EventHandler
    public void setPlayerStats(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(!p.hasPlayedBefore())mySQL.createNewStats(p.getName(),500,250,0,p.getUniqueId().toString());
        int[] stats = mySQL.getStats(p.getName());
        food.put(p,stats[0]);
        water.put(p,stats[1]);
        radiation.put(p,stats[2]);
    }
    @EventHandler
    public void quitRemovePlayerStats(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if(p.isOp())return;
        mySQL.setStats(p.getName(),food.get(p),water.get(p), radiation.get(p));
        water.remove(p);
        food.remove(p);
        radiation.remove(p);
    }


    @EventHandler
    public void respawnPlayerStats(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        if(p.hasPermission("rustcore.metabolism")){
            food.put(p,500);
            water.put(p,250);
            radiation.put(p,0);
        }else {
            food.put(p,200);
            water.put(p,100);
            radiation.put(p,0);
        }
    }

    public static int getWater(Player p){
        return water.get(p);
    }
    public static int getFood(Player p){
        return food.get(p);
    }
    public static int getRadiation(Player p){
        return radiation.get(p);
    }

    public static int setWater(Player p,int waterValue){
        return water.put(p,waterValue);
    }
    public static int setFood(Player p,int foodValue){
        return food.put(p,foodValue);
    }
    public static int setRadiation(Player p,int radiationValue){
        return radiation.put(p,radiationValue);
    }


}
